package com.cirkuits.cirkuitsapi.stripe.model;

public class StripeSubscriptionResponse {
    private StripeSubscriptionMapper subscription;

    public StripeSubscriptionResponse() {}

    public StripeSubscriptionMapper getSubscription() {
        return subscription;
    }

    public void setSubscription(StripeSubscriptionMapper subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return subscription.toString();
    }
}
