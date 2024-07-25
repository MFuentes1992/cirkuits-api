package com.cirkuits.cirkuitsapi.stripe.model;

import java.util.UUID;

public class StripeCustomer {
    private String customerName;
    private String customerEmail;

    public StripeCustomer() {
    }

    public StripeCustomer(String customerName, String customerEmail) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public String toString() {
        return "StripeCustomer { "+ "customerName='" + customerName + '\'' + ", customerEmail='" + customerEmail + '\'' + '}';
    }
}
