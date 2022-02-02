package com.teamAlpha.bookHub.catalog.service;

import com.teamAlpha.bookHub.catalog.controller.ProductController;
import com.teamAlpha.bookHub.catalog.entity.Product;
import com.teamAlpha.bookHub.catalog.entity.ProductCategory;
import com.teamAlpha.bookHub.catalog.exception.ProductCategoryNotFoundException;
import com.teamAlpha.bookHub.catalog.exception.ProductNotFoundException;
import com.teamAlpha.bookHub.catalog.model.DeleteMessage;
import com.teamAlpha.bookHub.catalog.model.ProductDto;
import com.teamAlpha.bookHub.catalog.repository.ProductCategoryRepository;
import com.teamAlpha.bookHub.catalog.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public ProductDto productDetails (Integer productId)throws ProductNotFoundException {
        try{
            ProductDto productDto = new ProductDto();
            Product product = productRepository.findById(productId).get();
            System.out.println(product);
//            ProductCategory productCategory = productCategoryRepository.findById()
            return null;
        }catch (Exception e){
            throw new ProductNotFoundException(productId);
        }
    }

    public ProductDto createProduct (Product product) throws ProductCategoryNotFoundException {

        try{
            ProductDto productDto = new ProductDto();
            Product product1 = new Product();
            ProductCategory productCategory = productCategoryRepository.findById(product.getProductCategoryId()).get();
            BeanUtils.copyProperties(product, product1);
            product1.setProductCategory(productCategory);
            Product product2 = productRepository.save(product1);
            BeanUtils.copyProperties(product2, productDto);
            productDto.add(linkTo(methodOn(ProductController.class).getProductDetail(product.getProductId())).withSelfRel());
            productDto.add(linkTo(methodOn(ProductController.class).getAllProduct()).withRel("list"));
            return productDto;

        }catch (Exception e){
            throw new ProductCategoryNotFoundException(product.getProductCategoryId());
        }

    }

    public DeleteMessage deleteProduct(Integer productId) throws ProductNotFoundException{
        try {
            DeleteMessage deleteMessage = new DeleteMessage();
            productRepository.deleteById(productId);
            deleteMessage.setMessage("Product of id "+ productId + " is deleted from database");
            deleteMessage.add(linkTo(methodOn(ProductController.class).getAllProduct()).withRel("list"));
            return deleteMessage;

        }catch (Exception e){
            throw new ProductNotFoundException(productId);
        }
    }

    public List<ProductDto> getAllProduct (){
        return null;
    }

}
