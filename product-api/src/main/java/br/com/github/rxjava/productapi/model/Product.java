package br.com.github.rxjava.productapi.model;

import java.math.BigDecimal;

public class Product {

    private String name;

    private String description;

    private BigDecimal price;

    private Category category;

    public Product(){}

    public Product(String name, String description, BigDecimal price, Category category){
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
