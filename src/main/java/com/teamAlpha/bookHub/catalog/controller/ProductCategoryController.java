package com.teamAlpha.bookHub.catalog.controller;

import com.teamAlpha.bookHub.catalog.entity.ProductCategory;
import com.teamAlpha.bookHub.catalog.model.ProductCategoryDto;
import com.teamAlpha.bookHub.catalog.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/category")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping()
    public String testApi(){
        return "Server is running";
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ProductCategoryDto> createProductCategory (@Valid @RequestBody ProductCategory productCategory) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                        .body(productCategoryService.createProductCategory(productCategory));

    }

    @GetMapping(path = "/list")
    public  ResponseEntity<List<ProductCategory>> GetProductCategory(){
        List<ProductCategory> list = productCategoryService.getAllProductCategory();

        return ResponseEntity.status(HttpStatus.OK)
                .body(list);
    }

    @DeleteMapping(path = "/{id}/delete")
    public String deleteProductCategory(@PathVariable("id") Integer productCategoryId) {
        return productCategoryService.deleteProductCategory(productCategoryId);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> productCategoryDetails(@PathVariable("id") Integer productCategoryId) {
            return new ResponseEntity <>( productCategoryService.productCategoryDetail(productCategoryId), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}/update")
    public ProductCategoryDto updateProductCategory(@PathVariable("id") Integer productCategoryId, @RequestBody ProductCategory productCategory){
        return productCategoryService.updateProductCategoryDetails(productCategoryId, productCategory);
    }

    @GetMapping(path = "all")
    public CollectionModel<EntityModel<ProductCategory>> all(){
        return productCategoryService.all();
    }

    @GetMapping(path = "pcall")
    public ResponseEntity<?> produccc(){
        return new ResponseEntity<>(productCategoryService.getAllProductCategory(), HttpStatus.OK);
    }

}
