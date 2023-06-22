package org.lessons.java.springlamiapizzeriacrud.model;

import java.math.BigDecimal;

public class Pizza {
    private String name;
    private String description;
    private String urlPhoto;
    private BigDecimal price;

    public Pizza(String name, String description, String urlPhoto, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.urlPhoto = urlPhoto;
        this.price = price;
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

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
