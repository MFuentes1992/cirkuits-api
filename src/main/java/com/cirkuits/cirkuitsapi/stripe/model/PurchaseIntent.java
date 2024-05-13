package com.cirkuits.cirkuitsapi.stripe.model;

public class PurchaseIntent {
    private String email;
    private String currency;
    private Long amount;

    private String locale;

    public PurchaseIntent(String email, String fullName, String phoneNumber, String currency, Long amount, String locale) {
        this.email = email;
        this.currency = currency;
        this.amount = amount;
        this.locale = locale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return "PurchaseIntent{" +
                "email='" + email + '\'' +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                ", locale='" + locale + '\'' +
                '}';
    }
}
