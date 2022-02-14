package com.teamAlpha.bookHub.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamAlpha.bookHub.catalog.entity.ProductReview;
import com.teamAlpha.bookHub.catalog.model.ProductReviewDto;
import com.teamAlpha.bookHub.catalog.service.ProductReviewService;

@RestController
@RequestMapping("api/v1/productReview")
public class ProductReviewController {
	
	@Autowired
	ProductReviewService productReviewService; 
	
	@PostMapping("/save")
	public ResponseEntity<ProductReviewDto> saveProductReview(@RequestBody ProductReviewDto productReviewDto) {
		return ResponseEntity.status(HttpStatus.OK).body(productReviewService.saveProductReview(productReviewDto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductReviewDto> findProductReviewById(@PathVariable("id") Integer productReviewId) {
		return ResponseEntity.status(HttpStatus.OK).body(productReviewService.productReviewFindById(productReviewId));
	}
	
	@GetMapping("list")
	public ResponseEntity<List<ProductReviewDto>> getAllProductReview() {
		return ResponseEntity.status(HttpStatus.OK).body(productReviewService.getAllProductReview());
	}
	
	@PutMapping(path = "/update/{id}")
    public ResponseEntity<ProductReviewDto> updateProductReview (@PathVariable("id") Integer productReviewId, @RequestBody ProductReview productReview){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productReviewService.updateProductReview(productReviewId, productReview));
    }
	
	@DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?>deleteProductReview(@PathVariable("id") Integer productReviewId){
        return new ResponseEntity<>(productReviewService.deleteProductReviewById(productReviewId), HttpStatus.OK);
    }

}
