package com.cirkuits.cirkuitsapi.stripe.model;

import java.util.ArrayList;

public class StripeProductResponse {
    private ArrayList<StripeProductMapper> products;

    public StripeProductResponse() {

    }

    public void setProducts(ArrayList<StripeProductMapper> products) {
        this.products = products;
    }
    public ArrayList<StripeProductMapper> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "StripeProductResponse = {" +
                "Products = " + products.toString() +
                "}";
    }
}
