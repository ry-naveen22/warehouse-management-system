package com.wh.managementSystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProductsHelper {

    @JsonProperty(value="products")
    private List<Product> products;

    public ProductsHelper() {
    }

    public ProductsHelper(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
