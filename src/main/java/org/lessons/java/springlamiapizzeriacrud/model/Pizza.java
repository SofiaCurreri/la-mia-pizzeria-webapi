package org.lessons.java.springlamiapizzeriacrud.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity //identifica la classe Pizza come entità
@Table(name = "pizzas") //rinomino tabella al plurale
public class Pizza {
    @Id //rende Integer id chiave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //per autoincrement
    private Integer id;

    @Column(nullable = false)
    private String name;
    private String description;
    private String urlPhoto;
    @Column(nullable = false)
    private BigDecimal price;
    private LocalDateTime createdAt;

    public Pizza() {
    }

    public Integer getId() {
        return id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
