package com.cirkuits.cirkuitsapi.stripe.model;

import com.cirkuits.cirkuitsapi.stripe.model.cancelation.CancelationModel;

public class SubscriptionModel {
    private String id;
    private String status;
    private long periodStart;
    private long periodEnd;
    private long cancelAt;
    private long canceledAt;
    private Long daysUntilDue;
    private CancelationModel cancelationDetails;


    public SubscriptionModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public CancelationModel getCancelationDetails() {
        return cancelationDetails;
    }

    public void setCancelationDetails(CancelationModel cancelationDetails) {
        this.cancelationDetails = cancelationDetails;
    }

    public Long getDaysUntilDue() {
        return daysUntilDue;
    }

    public void setDaysUntilDue(Long daysUntilDue) {
        this.daysUntilDue = daysUntilDue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
         return "SubscriptionModel={" +
                  "id='" + id + '\'' +
                  ", status='" + status + '\'' +
                  ", periodStart=" + periodStart + '\'' +
                  ", periodEnd=" + periodEnd + '\'' +
                  ", cancelAt=" + cancelAt + '\'' +
                  ", canceledAt=" + canceledAt + '\'' +
                  ", cancelationDetails=" + cancelationDetails.toString() + '\'' +
                  ", daysUntilDue=" + daysUntilDue + '\'' +
                 " }";
    }
}
