package com.teamAlpha.bookHub.catalog.model;

import org.springframework.hateoas.RepresentationModel;

public class ProductReviewDto extends RepresentationModel<ProductReviewDto> {

	private Integer reviewId;
	private Integer productId;
	private Integer userId;
	private Integer ratingValue;
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
		return "ProductReviewDto [reviewId=" + reviewId + ", productId=" + productId + ", userId=" + userId
				+ ", ratingValue=" + ratingValue + ", reviewMessage=" + reviewMessage + "]";
	}

}
