package com.teamAlpha.bookHub.catalog.service;

import com.teamAlpha.bookHub.catalog.controller.ProductCategoryController;
import com.teamAlpha.bookHub.catalog.entity.ProductCategory;
import com.teamAlpha.bookHub.catalog.exception.ProductCategoryNotFoundException;
import com.teamAlpha.bookHub.catalog.model.ProductCategoryDto;
import com.teamAlpha.bookHub.catalog.repository.ProductCategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductCategoryService {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public ProductCategoryDto createProductCategory (ProductCategory productCategory){
        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
        ProductCategory productCategory1 = productCategoryRepository.save(productCategory);
        BeanUtils.copyProperties(productCategory1, productCategoryDto);

        productCategoryDto.add(linkTo(methodOn(ProductCategoryController.class).GetProductCategory()).withRel("list"));
        productCategoryDto.add(linkTo(methodOn(ProductCategoryController.class).productCategoryDetails(productCategory.getCategoryId())).withSelfRel());
        return productCategoryDto;
    }

    public ProductCategoryDto productCategoryDetail(Integer productCategoryId) throws ProductCategoryNotFoundException {
        try{

            ProductCategoryDto productCategoryDto = new ProductCategoryDto();
            ProductCategory productCategory = productCategoryRepository.findById(productCategoryId).get();
            System.out.println(productCategory);
            BeanUtils.copyProperties(productCategory,productCategoryDto);
            productCategoryDto.add(linkTo(methodOn(ProductCategoryController.class).GetProductCategory()).withRel("list"));
            return productCategoryDto;

        }catch (Exception e){
            throw new ProductCategoryNotFoundException(productCategoryId);
        }
    }



    public List<ProductCategory> getAllProductCategory (){
        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
        List<ProductCategory> listCategory = productCategoryRepository.findAll();
        return listCategory;
    }

    public String deleteProductCategory(Integer productCategoryId) throws ProductCategoryNotFoundException {

        try{
            productCategoryRepository.findById(productCategoryId).get();
            productCategoryRepository.deleteById(productCategoryId);
            return("Successfully delete product category" + productCategoryId + " Id ");

        }catch (Exception e){
            throw new ProductCategoryNotFoundException(productCategoryId);
        }

    }

    public ProductCategoryDto updateProductCategoryDetails(Integer productCategoryId, ProductCategory productCategory) throws ProductCategoryNotFoundException {

        try{
            ProductCategory productCategory1 = productCategoryRepository.findById(productCategoryId).get();
            ProductCategoryDto productCategoryDto = new ProductCategoryDto();

                if(Objects.nonNull(productCategory.getCategoryName())&& !"".equalsIgnoreCase(productCategory.getCategoryName())){
                    productCategory1.setCategoryName(productCategory.getCategoryName());
                }
                if(Objects.nonNull(productCategory.getDescription())&& !"".equalsIgnoreCase(productCategory.getDescription())){
                    productCategory1.setDescription(productCategory.getDescription());
                }
                if(Objects.nonNull(productCategory.getShopId())){
                    productCategory1.setShopId(productCategory.getShopId());
                }

                ProductCategory productCategory2 = productCategoryRepository.save(productCategory1);
                BeanUtils.copyProperties(productCategory2, productCategoryDto);
            productCategoryDto.add(linkTo(methodOn(ProductCategoryController.class).GetProductCategory()).withRel("list"));
            productCategoryDto.add(linkTo(methodOn(ProductCategoryController.class).productCategoryDetails(productCategory2.getCategoryId())).withSelfRel());
            return productCategoryDto;
        }catch (Exception e){
            throw new ProductCategoryNotFoundException(productCategoryId);

        }
    }

}
