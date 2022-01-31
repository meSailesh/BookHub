package com.teamAlpha.bookHub.catalog.entity;

import com.teamAlpha.bookHub.common.entity.Schemas;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "product_category", schema = Schemas.CATALOG)

public class ProductCategory {
    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "category_id")
    private Integer categoryId;
    @NotNull
    @Size(min = 2, message = "Category name should have at least 2 characters")
    @Column(name = "category_name")
    private String categoryName;
    @NotNull
    @Column (name = "description")
    private String description;
    @NotNull
    @Column (name = "shop_id")
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

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", shopId=" + shopId +
                '}';
    }
}
