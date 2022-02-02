package com.teamAlpha.bookHub.catalog.model;

import com.teamAlpha.bookHub.catalog.entity.Product;
import org.springframework.hateoas.RepresentationModel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductCategoryDto extends RepresentationModel <ProductCategoryDto>{
    private Integer categoryId;
    private String categoryName;
    private String description;
    private Integer shopId;
    private List<Product> products;


    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
