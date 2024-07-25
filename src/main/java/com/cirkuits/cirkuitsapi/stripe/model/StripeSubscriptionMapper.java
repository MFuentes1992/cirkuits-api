package com.cirkuits.cirkuitsapi.stripe.model;

import com.cirkuits.cirkuitsapi.stripe.model.cancelation.CancelationModel;

public class StripeSubscriptionMapper {
    private String subscriptionId;
    private String userId;
    private String customerId;
    private String status;
    private long periodStart;
    private long periodEnd;
    private long cancelAt;
    private long canceledAt;
    private long daysUntilDue;
    private CancelationModel cancelationDetails;

    public StripeSubscriptionMapper() {}

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(long periodStart) {
        this.periodStart = periodStart;
    }

    public long getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(long periodEnd) {
        this.periodEnd = periodEnd;
    }

    public long getCancelAt() {
        return cancelAt;
    }

    public void setCancelAt(long cancelAt) {
        this.cancelAt = cancelAt;
    }

    public long getCanceledAt() {
        return canceledAt;
    }

    public void setCanceledAt(long canceledAt) {
        this.canceledAt = canceledAt;
    }

    public long getDaysUntilDue() {
        return daysUntilDue;
    }

    public void setDaysUntilDue(long daysUntilDue) {
        this.daysUntilDue = daysUntilDue;
    }

    public CancelationModel getCancelationDetails() {
        return cancelationDetails;
    }

    public void setCancelationDetails(CancelationModel cancelationDetails) {
        this.cancelationDetails = cancelationDetails;
    }

    @Override
    public String toString() {
        return "StripeSubscriptionMappe = {" +
                 "subscriptionId='" + subscriptionId + '\'' +
                 ", userId='" + userId + '\'' +
                 ", customerId='" + customerId + '\'' +
                 ", status='" + status + '\'' +
                 ", periodStart=" + periodStart + '\'' +
                 ", periodEnd=" + periodEnd + '\'' +
                 ", cancelAt=" + cancelAt + '\'' +
                 ", canceledAt=" + canceledAt + '\'' +
                 ", daysUntilDue=" + daysUntilDue + '\'' +
                 ", cancelationDetails=" + cancelationDetails.toString() + '\'' +
                "   }";
    }
}
