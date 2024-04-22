package com.cirkuits.cirkuitsapi.stripe.model;

public class StripeResponse {
    private String clientSecret;
    private String ephemeralKey;
    private String customerId;

    public StripeResponse(String clientSecret, String ephemeralKey, String customerId) {
        this.clientSecret = clientSecret;
        this.ephemeralKey = ephemeralKey;
        this.customerId = customerId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getEphemeralKey() {
        return ephemeralKey;
    }

    public void setEphemeralKey(String ephemeralKey) {
        this.ephemeralKey = ephemeralKey;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "StripeResponse{" +
                "clientSecret='" + clientSecret + '\'' +
                ", ephemeralKey='" + ephemeralKey + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
