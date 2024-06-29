package com.cirkuits.cirkuitsapi.stripe.model;

public class StripeCustomerResponse {
    private String customerId;
    private String customerName;

    public StripeCustomerResponse() {

    }
    public StripeCustomerResponse(String customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "StripeCustomerResponse: { "+ customerId + ", " + customerName + '}';
    }
}
