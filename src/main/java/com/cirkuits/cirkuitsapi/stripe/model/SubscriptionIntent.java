package com.cirkuits.cirkuitsapi.stripe.model;

public class SubscriptionIntent {
    private String priceId;
    private String customerId;

    public SubscriptionIntent(String priceId, String customerId) {
        this.priceId = priceId;
        this.customerId = customerId;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "SubscriptionIntent{" +
                "priceId='" + priceId + '\'' +
                "customerID" + customerId + '\'' +
                "}";
    }
}
