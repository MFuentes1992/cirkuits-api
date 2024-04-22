package com.cirkuits.cirkuitsapi.stripe.service;

import com.cirkuits.cirkuitsapi.stripe.model.StripeResponse;
import com.cirkuits.cirkuitsapi.user.Users;
import com.stripe.model.Customer;
import com.stripe.model.EphemeralKey;
import com.stripe.model.PaymentIntent;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.EphemeralKeyCreateParams;
import com.stripe.param.PaymentIntentCreateParams;

public class StripeService {
    private Users user;
    private String currency;
    private Long amount;

    public StripeService(Users user, String currency, Long amount) {
        this.user = user;
        this.currency = currency;
        this.amount = amount;
    }

    public Customer createCustomer(Users _user) throws Exception {
        CustomerCreateParams customerCreateParams = new CustomerCreateParams.Builder()
                .setEmail(_user.getEmail())
                .setName(_user.getFullName())
                .setPhone(_user.getMobile())
                .build();
        return Customer.create(customerCreateParams);
    }

    public EphemeralKey createEphemeralKey(Customer customer) throws Exception {
        EphemeralKeyCreateParams keyCreateParams = new EphemeralKeyCreateParams.Builder()
                .setCustomer(customer.getId())
                .setStripeVersion("2020-08-27")
                .build();
        return EphemeralKey.create(keyCreateParams);
    }

    public StripeResponse createPaymentIntent() throws Exception {
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency(currency)
                .setAmount(amount)
                .setAutomaticPaymentMethods(PaymentIntentCreateParams.AutomaticPaymentMethods.builder().setEnabled(true).build())
                .build();
        PaymentIntent intent = PaymentIntent.create(createParams);
        EphemeralKey ephemeralKey = createEphemeralKey(createCustomer(user));
        Customer customer = createCustomer(user);
        return new StripeResponse(intent.getClientSecret(), ephemeralKey.getSecret(), customer.getId());
    }

}
