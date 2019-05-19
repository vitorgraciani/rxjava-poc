package br.com.github.rxjava.productapi.endpoint.Resource;

import java.math.BigDecimal;

public class ProductRequest {

    private String name;

    private String description;

    private BigDecimal price;

    private CategoryRequest categoryRequest;

    public ProductRequest(){}

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryRequest getCategoryRequest() {
        return categoryRequest;
    }

    public void setCategoryRequest(CategoryRequest categoryRequest) {
        this.categoryRequest = categoryRequest;
    }
}
