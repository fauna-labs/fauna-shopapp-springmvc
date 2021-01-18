package com.parkhomenko.fauna.model;

import com.faunadb.client.types.FaunaConstructor;
import com.faunadb.client.types.FaunaField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Product {
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    private Category category;
    private Integer quantity;

    @FaunaConstructor
    public Product(
            @FaunaField("id") String id,
            @FaunaField("name") String name,
            @FaunaField("price") double price,
            @FaunaField("description") String description,
            @FaunaField("category") Category category,
            @FaunaField("quantity") int quantity
    ) {
        this.id = id;
        this.name = name;
        this.price = BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);
        this.description = description;
        this.category = category;
        this.quantity = quantity;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) && name.equals(product.name) && Objects.equals(price, product.price) && Objects.equals(description, product.description) && Objects.equals(category, product.category) && Objects.equals(quantity, product.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description, category, quantity);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", quantity=" + quantity +
                '}';
    }
}
