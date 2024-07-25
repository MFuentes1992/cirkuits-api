package com.cirkuits.cirkuitsapi.stripe.model.cancelation;


public class CancelationModel {
    private String reason;
    private String feedback;
    private String comment;

    public CancelationModel(String reason, String feedback, String comment) {
        this.reason = reason;
        this.feedback = feedback;
        this.comment = comment;
    }

    public CancelationModel() {

    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CancelationModel={" +
                 "reason='" + reason + '\'' +
                 ", feedback='" + feedback + '\'' +
                 ", comment='" + comment + '\'' +
                " }";
    }
}
