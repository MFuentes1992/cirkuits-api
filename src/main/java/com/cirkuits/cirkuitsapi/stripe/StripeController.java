package com.cirkuits.cirkuitsapi.stripe;

import com.cirkuits.cirkuitsapi.stripe.model.PurchaseIntent;
import com.cirkuits.cirkuitsapi.stripe.service.StripeService;
import com.cirkuits.cirkuitsapi.user.UserService;
import com.cirkuits.cirkuitsapi.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;

@RestController
public class StripeController {
    @Autowired
    UserService userService;

    @PostMapping (path = "api/v1/stripe/payment-sheet")
    public ResponseEntity<Object> paymentSheet(@RequestBody PurchaseIntent pIntent) throws Exception {
        Stripe.apiKey = "sk_test_51HTgMwHZLa5RkL3dWhGh7hXaAPV61rP2GdxT2xigI4sMEOR94B2DNYmMY30LCpOgCtC7l5gEmvVUeyYqreqnRftA00p1FraSUn";
        // -- If customer Id does not exist in DB, create a new customer
        // -- Customer must have full name, email, and phone number
        Users user = userService.getUserEmail(pIntent.getEmail());
        StripeService stripeService = new StripeService(user, pIntent.getCurrency(), pIntent.getAmount());
        return ResponseEntity.ok().body(stripeService.createPaymentIntent());
    }
}
