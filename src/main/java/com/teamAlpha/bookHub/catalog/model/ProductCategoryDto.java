package com.teamAlpha.bookHub.catalog.model;

import org.springframework.hateoas.RepresentationModel;


public class ProductCategoryDto extends RepresentationModel <ProductCategoryDto>{
    private Integer categoryId;
    private String categoryName;
    private String description;
    private Integer shopId;



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

}
