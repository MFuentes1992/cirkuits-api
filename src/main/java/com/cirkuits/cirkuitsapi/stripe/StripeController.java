package com.cirkuits.cirkuitsapi.stripe;

import com.auth0.jwk.JwkException;
import com.cirkuits.cirkuitsapi.Auth.AuthService;
import com.cirkuits.cirkuitsapi.stripe.model.*;
import com.cirkuits.cirkuitsapi.stripe.model.error.StripeErrorResponse;
import com.cirkuits.cirkuitsapi.stripe.service.StripePriceService;
import com.cirkuits.cirkuitsapi.stripe.service.StripeService;
import com.cirkuits.cirkuitsapi.user.UserService;
import com.cirkuits.cirkuitsapi.user.Users;
import com.stripe.model.Customer;
import com.stripe.model.StripeError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import com.stripe.Stripe;

@RestController
public class StripeController {
    @Autowired
    UserService userService;
    @Autowired
    StripeService stripeService;
    @Autowired
    StripePriceService priceService;
    @Value("${cirkuits.stripe.secret.key}")
    private String secretKey;
    @Value("${cirkuits.auth0.jwks.uri}")
    private String jwkUri;

    @PostMapping (path = "api/v1/stripe/payment-sheet")
    public ResponseEntity<Object> paymentSheet(@RequestBody PurchaseIntent pIntent) throws Exception {
        Stripe.apiKey = secretKey;
        Users user = userService.getUserEmail(pIntent.getEmail());
        if(user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        stripeService.Initialize(user, pIntent.getCurrency(), pIntent.getAmount(), pIntent.getLocale());
        return ResponseEntity.ok().body(stripeService.createPaymentIntent());
    }

    @PostMapping(path = "api/v1/stripe/create-subscription")
    public ResponseEntity<StripeBillingResponse> createSubscription(@RequestBody SubscriptionIntent sbcIntent) throws Exception {
        Stripe.apiKey = secretKey;
        return ResponseEntity.ok().body(stripeService.createSubscriptionIntent(sbcIntent.getPriceId(), sbcIntent.getCustomerId()));
    }

    @PostMapping(path = "api/v1/create-customer")
    public ResponseEntity<Object> createCustomer(@RequestHeader("Authorization") String bearerToken, @RequestBody StripeCustomer sCustomer) throws Exception {
        AuthService authService = new AuthService(bearerToken.substring(7), jwkUri);
        if(!authService.isValidToken() || authService.isTokenExpired()) {
            StripeErrorResponse errorResponse = new StripeErrorResponse("Token is invalid or expired.");
            return ResponseEntity.badRequest().body(errorResponse);
        };
        Stripe.apiKey = secretKey;
        StripeCustomerResponse response = stripeService.createStripeCustomer(sCustomer.getCustomerEmail());
        if(response == null) {
            StripeErrorResponse errorResponse = new StripeErrorResponse("The email does not exist.");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("api/v1/products")
    public ResponseEntity<Object> getProducts(@RequestHeader("Authorization") String bearerToken) throws Exception {
        AuthService authService = new AuthService(bearerToken.substring(7), jwkUri);
        if(!authService.isValidToken() || authService.isTokenExpired()) {
            StripeErrorResponse errorResponse = new StripeErrorResponse("Token is invalid or expired.");
            return ResponseEntity.badRequest().body(errorResponse);
        };
        Stripe.apiKey = secretKey;
        StripeProductResponse response = priceService.getProductMapper();
        if(response == null) {
            StripeErrorResponse errorResponse = new StripeErrorResponse("Couldn't get product list.");
        }
        return ResponseEntity.ok().body(response);
    }
}
