package com.cirkuits.cirkuitsapi.customerPurchase.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "tbl_CustomerPurchase")
public class CustomerPurchase {
    @Id
    @UuidGenerator
    private UUID purchaseId;
    private UUID userId;
    private String currency;

    private String locale;
    private UUID addressId;

    private String stripeCustomerId;
    private String stripeSubscriptionId;

    public CustomerPurchase() {
    }

    public CustomerPurchase(UUID userId, String currency, String locale, UUID addressId, String stripeId, String currentSubscriptionId) {
        this.purchaseId = UUID.randomUUID();
        this.userId = userId;
        this.currency = currency;
        this.addressId = addressId;
        this.stripeCustomerId = stripeId;
        this.stripeSubscriptionId = currentSubscriptionId;
        this.locale = locale;
    }

    public UUID getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(UUID purchaseId) {
        this.purchaseId = purchaseId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    public String getStripeCustomerId() {
        return stripeCustomerId;
    }

    public void setStripeCustomerId(String stripeId) {
        this.stripeCustomerId = stripeId;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCurrentSubscription() {
        return stripeSubscriptionId;
    }

    public void setCurrentSubscription(String currentSubscription) {
        this.stripeSubscriptionId = currentSubscription;
    }

    @Override
    public String toString() {
        return "CustomerPurchase{" +
                "purchaseId=" + purchaseId +
                ", userId=" + userId +
                ", currency='" + currency + '\'' +
                ", addressId='" + addressId + '\'' +
                ", stripeId='" + stripeCustomerId + '\'' +
                ", currentSubscription='" + stripeSubscriptionId + '\'' +
                ", locale='" + locale + '\'' +
                '}';
    }
}
