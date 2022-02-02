package com.teamAlpha.bookHub.catalog.entity;

import com.teamAlpha.bookHub.common.entity.Schemas;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "product", schema = Schemas.CATALOG)
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "product_id")
    private Integer productId;
    @NotNull
    @Size(min = 2, message = "Product name should have at least 2 characters")
    @Column(name = "product_name")
    private String productName;
    @NotNull
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "price")
    private Integer price;
    @NotNull
    @Column(name = "image_id")
    private Integer imageId;
    @NotNull
    @Column(name = "available_count")
    private Integer availableCount;

    @NotNull
    @Column(name = "shop_id")
    private Integer shopId;


    @Transient
    private Integer productCategoryId;

//    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    //name can be anything but ref name should be @Colum name or id
    @JoinColumn(name = "product_category_id", referencedColumnName = "category_id")
    private ProductCategory productCategory;

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Integer availableCount) {
        this.availableCount = availableCount;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageId=" + imageId +
                ", availableCount=" + availableCount +
                ", shopId=" + shopId +
                ", productCategory=" + productCategory +
                '}';
    }
}
