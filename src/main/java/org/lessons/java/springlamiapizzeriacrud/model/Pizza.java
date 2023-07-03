package org.lessons.java.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity //identifica la classe Pizza come entità
@Table(name = "pizzas") //rinomino tabella al plurale
public class Pizza {
    @Id //rende Integer id chiave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //per autoincrement
    private Integer id;

    @NotBlank(message = "Name must not be blank") //per dire che non puo essere una stringa vuota
    @Column(nullable = false)
    private String name;
    private String description;
    private String urlPhoto;
    @NotNull(message = "Price must not be null")
    @DecimalMin(value = "0.00", message = "Price must be > 0.00 €")
    @Column(nullable = false)
    private BigDecimal price;

    @OneToMany(mappedBy = "pizza", cascade = {CascadeType.REMOVE})
    //pizza è il nome dell' attributo con annotation Many to One in SpecialDeal.java
    //inizializzo come lista vuota per evitare situa in cui lista sia null
    private List<SpecialDeal> specialDeals = new ArrayList<>();

    @ManyToMany()
    @JoinTable(name = "pizza_ingredients", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;
    private LocalDateTime createdAt;

    public Pizza() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<SpecialDeal> getSpecialDeals() {
        return specialDeals;
    }

    public void setSpecialDeals(List<SpecialDeal> specialDeals) {
        this.specialDeals = specialDeals;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
