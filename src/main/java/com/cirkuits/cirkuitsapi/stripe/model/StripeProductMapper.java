package com.cirkuits.cirkuitsapi.stripe.model;

public class StripeProductMapper {
    private String id;
    private String name;
    private String description;
    private String object;
    private String duration;
    private String currency;
    private boolean active;
    private double created;
    private boolean liveMode;
    private double updated;
    private double price; // -- Price is mapped when we retrieve a price object related to this product instance.


    public StripeProductMapper() {}

    public StripeProductMapper(String id, String name, String description, String object, String duration, String currency,boolean active, double created, boolean liveMode, double updated, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.object = object;
        this.active = active;
        this.created = created;
        this.liveMode = liveMode;
        this.updated = updated;
        this.price = price;
        this.duration = duration;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getCreated() {
        return created;
    }

    public void setCreated(double created) {
        this.created = created;
    }

    public boolean isLiveMode() {
        return liveMode;
    }

    public void setLiveMode(boolean liveMode) {
        this.liveMode = liveMode;
    }

    public double getUpdated() {
        return updated;
    }

    public void setUpdated(double updated) {
        this.updated = updated;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "StripeCustomerMapper {"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", object='" + object + '\''
                + ", active=" + active + '\''
                + ", created=" + created + '\''
                + ", liveMode=" + liveMode + '\''
                + ", updated=" + updated + '\''
                + ", duration=" + duration + '\''
                + ", currency='" + currency + '\''
                + ", updated=" + updated + '\''
                + ", price=" + price + '\'';
    }
}
