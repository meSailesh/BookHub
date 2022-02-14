package com.teamAlpha.bookHub.catalog.exception;

public class ProductReviewNotFoundException extends RuntimeException {
	
	public ProductReviewNotFoundException(Integer reviewId){
        super("Couldn't find any product review with id "+ reviewId +" in our records. Try again");
    }

}
