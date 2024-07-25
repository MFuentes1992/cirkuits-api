package com.cirkuits.cirkuitsapi.stripe.model.error;

public class StripeErrorResponse {
    private String error;

    public StripeErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Error{ "+error+" }";
    }
}
