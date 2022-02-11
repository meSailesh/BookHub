package com.teamAlpha.bookHub.catalog.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teamAlpha.bookHub.common.entity.Schemas;

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
    
//    @JsonIgnore
    @ManyToOne()
    //name can be anything but ref name should be @Colum name or id
    @JoinColumn(name = "product_category_id", referencedColumnName = "category_id")
    private ProductCategory productCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<ProductReview> productReview; 

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

    public List<ProductReview> getProductReview() {
		return productReview;
	}


	public void setProductReview(List<ProductReview> productReview) {
		this.productReview = productReview;
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
                ", ProductReviw=" + productReview +
                '}';
    }
}
