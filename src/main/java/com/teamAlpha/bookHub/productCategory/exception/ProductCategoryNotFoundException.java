package com.teamAlpha.bookHub.productCategory.exception;

import org.springframework.stereotype.Component;

public class ProductCategoryNotFoundException extends RuntimeException{

    public ProductCategoryNotFoundException(Integer productCategoryId){
        super("Couldn't find any student with id "+ productCategoryId +" in our records. Try again");
    }
}
