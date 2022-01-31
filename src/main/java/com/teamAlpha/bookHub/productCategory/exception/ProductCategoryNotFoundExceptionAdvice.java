package com.teamAlpha.bookHub.productCategory.exception;

import com.teamAlpha.bookHub.productCategory.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice
public class ProductCategoryNotFoundExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(value = ProductCategoryNotFoundException.class)
    public ResponseEntity<?> handleProductCategoryNotFound(ProductCategoryNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                new Date(),
                HttpStatus.BAD_REQUEST.getReasonPhrase()
        );
        return new ResponseEntity <>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}