package com.cirkuits.cirkuitsapi.stripe;

import com.cirkuits.cirkuitsapi.stripe.model.PurchaseIntent;
import com.cirkuits.cirkuitsapi.stripe.model.StripeBillingResponse;
import com.cirkuits.cirkuitsapi.stripe.model.SubscriptionIntent;
import com.cirkuits.cirkuitsapi.stripe.service.StripeService;
import com.cirkuits.cirkuitsapi.user.UserService;
import com.cirkuits.cirkuitsapi.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;

@RestController
public class StripeController {
    @Autowired
    UserService userService;
    @Autowired
    StripeService stripeService;
    @Value("${cirkuits.stripe.secret.key}")
    private String secretKey;
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
}
