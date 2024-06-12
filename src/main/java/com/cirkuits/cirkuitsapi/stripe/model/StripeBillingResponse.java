package com.cirkuits.cirkuitsapi.stripe.model;

public class StripeBillingResponse {
    private String subscriptionId;
    private String clientSecret;

    public StripeBillingResponse(String subscriptionId, String clientSecret) {
        this.subscriptionId = subscriptionId;
        this.clientSecret = clientSecret;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public String toString() {
        return "SubscriptionId: " + subscriptionId + ", ClientSecret: " + clientSecret;
    }
}
