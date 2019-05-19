package br.com.github.rxjava.productapi.infrastructure.repository.entity;

import br.com.github.rxjava.productapi.model.Category;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name ="price")
    private BigDecimal price;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CategoryEntity category;

    public ProductEntity(){ }

    public ProductEntity(String name, String description, BigDecimal price, CategoryEntity categoryEntity){
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = categoryEntity;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
