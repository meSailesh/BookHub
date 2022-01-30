package com.teamAlpha.bookHub.productCategory.controller;

import com.teamAlpha.bookHub.productCategory.entity.ProductCategory;
import com.teamAlpha.bookHub.productCategory.exception.ProductCategoryNotFoundException;
import com.teamAlpha.bookHub.productCategory.model.ErrorResponse;
import com.teamAlpha.bookHub.productCategory.model.ProductCategoryDto;
import com.teamAlpha.bookHub.productCategory.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/category/")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping()
    public String testApi(){
        return "Server is running";
    }

    @PostMapping(path = "create")
    public ResponseEntity<ProductCategoryDto> createProductCategory (@Valid @RequestBody ProductCategory productCategory){
        return ResponseEntity.status(HttpStatus.OK)
                        .body(productCategoryService.createProductCategory(productCategory));

    }

    @GetMapping(path = "list")
    public  ResponseEntity<List<ProductCategory>> GetProductCategory(){
        List<ProductCategory> list = productCategoryService.getAllProductCategory();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(list);
    }

    @DeleteMapping(path = "{id}/delete")
    public String deleteProductCategory(@PathVariable("id") Integer productCategoryId) {
        return productCategoryService.deleteProductCategory(productCategoryId);
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<?> productCategoryDetails(@PathVariable("id") Integer productCategoryId) {
            return new ResponseEntity <>( productCategoryService.productCategoryDetail(productCategoryId), HttpStatus.OK);
    }

    @PutMapping(path = "{id}/update")
    public ProductCategoryDto updateProductCategory(@PathVariable("id") Integer productCategoryId, @RequestBody ProductCategory productCategory){
        return productCategoryService.updateProductCategoryDetails(productCategoryId, productCategory);
    }


}