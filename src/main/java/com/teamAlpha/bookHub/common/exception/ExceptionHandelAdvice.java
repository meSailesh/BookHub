package com.teamAlpha.bookHub.common.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teamAlpha.bookHub.catalog.exception.ProductCategoryNotFoundException;
import com.teamAlpha.bookHub.catalog.exception.ProductNotFoundException;
import com.teamAlpha.bookHub.catalog.exception.ProductReviewNotFoundException;
import com.teamAlpha.bookHub.common.model.ErrorResponse;
import com.teamAlpha.bookHub.communication.exception.AttachmentDetailNotFoundException;
import com.teamAlpha.bookHub.communication.exception.InvalidAttachmentTypeException;

@ControllerAdvice
public class ExceptionHandelAdvice {

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


    @ResponseBody
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<?> handleProductCategoryNotFound(ProductNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                new Date(),
                HttpStatus.BAD_REQUEST.getReasonPhrase()
        );
        return new ResponseEntity <>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ResponseBody
    @ExceptionHandler(value = InvalidAttachmentTypeException.class)
    public ResponseEntity<?> handleInvalidAttachmentType(InvalidAttachmentTypeException e) {
    	ErrorResponse errorResponse = new ErrorResponse(
    			HttpStatus.BAD_REQUEST.value(),
    			e.getMessage(), 
    			new Date(), 
    			HttpStatus.BAD_REQUEST.getReasonPhrase()
    	);
    	return new ResponseEntity<> (errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ResponseBody
    @ExceptionHandler(value = AttachmentDetailNotFoundException.class)
    public ResponseEntity<?> handleAttachDetailNotFound(AttachmentDetailNotFoundException e) {
    	ErrorResponse errorResponse = new ErrorResponse(
    			HttpStatus.BAD_REQUEST.value(), 
    			e.getMessage(), 
    			new Date(),
    			HttpStatus.BAD_REQUEST.getReasonPhrase()
    	);
    	return new ResponseEntity<> (errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ResponseBody
    @ExceptionHandler(value = ProductReviewNotFoundException.class)
    public ResponseEntity<?> handleProductReviewNotFoundException(ProductReviewNotFoundException e) {
    	ErrorResponse errorResponse = new ErrorResponse(
    			HttpStatus.BAD_REQUEST.value(), 
    			e.getMessage(), 
    			new Date(),
    			HttpStatus.BAD_REQUEST.getReasonPhrase()
    	);
    	return new ResponseEntity<> (errorResponse, HttpStatus.BAD_REQUEST);
    }
}
