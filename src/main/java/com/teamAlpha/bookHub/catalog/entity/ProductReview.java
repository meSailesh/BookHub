package com.teamAlpha.bookHub.catalog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.teamAlpha.bookHub.common.entity.Schemas;

@Entity
@Table(name = "product_review", schema = Schemas.CATALOG)
public class ProductReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private Integer reviewId;

	@Column(name = "product_id")
	private Integer productId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "rating_value")
	private Integer ratingValue;

	@Column(name = "review_message")
	private String reviewMessage;

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(Integer ratingValue) {
		this.ratingValue = ratingValue;
	}

	public String getReviewMessage() {
		return reviewMessage;
	}

	public void setReviewMessage(String reviewMessage) {
		this.reviewMessage = reviewMessage;
	}

	@Override
	public String toString() {
		return "ProductReview [reviewId=" + reviewId + ", productId=" + productId + ", userId=" + userId
				+ ", ratingValue=" + ratingValue + ", reviewMessage=" + reviewMessage + "]";
	}

}
