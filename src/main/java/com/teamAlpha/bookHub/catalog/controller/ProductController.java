package com.teamAlpha.bookHub.catalog.controller;

import com.teamAlpha.bookHub.catalog.entity.Product;
import com.teamAlpha.bookHub.catalog.model.DeleteMessage;
import com.teamAlpha.bookHub.catalog.model.ProductDto;
import com.teamAlpha.bookHub.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @PostMapping(path = "/create")
    public ResponseEntity<?> createProduct(@Valid  @RequestBody Product product){
        return ResponseEntity.status(HttpStatus.OK)
                        .body(productService.createProduct(product));
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getProductDetail(@PathVariable("id") Integer productId){
        return new ResponseEntity<>(productService.productDetails(productId),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<?>deleteProduct(@PathVariable("id") Integer productId){
        return new ResponseEntity<>(productService.deleteProduct(productId), HttpStatus.OK);
    }
//
    @GetMapping(path = "/list")
    public List<Product> getAllProduct(){
        return null;
    }


}
