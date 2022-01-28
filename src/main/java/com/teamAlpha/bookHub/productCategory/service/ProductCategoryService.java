package com.teamAlpha.bookHub.productCategory.service;

import com.teamAlpha.bookHub.productCategory.entity.ProductCategory;
import com.teamAlpha.bookHub.productCategory.repository.ProductCategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductCategoryService {
    @Autowired
    ProductCategoryRepository productCategoryRepository;


    public Boolean idExist(Integer productCategoryId){
        return productCategoryRepository.existsById(productCategoryId);
    }

    public ProductCategory findProductCategory(Integer productCategoryId){
        return productCategoryRepository.findById(productCategoryId).get();
    }

    public ProductCategory createProductCategory (ProductCategory productCategory){
        return productCategoryRepository.save(productCategory);
    }

    public List<ProductCategory> getAllProductCategory (){
        return productCategoryRepository.findAll();

    }

    public void deleteProductCategory(Integer productCategoryId) throws Exception {
        boolean isValid = idExist(productCategoryId);
        if(!isValid){
            throw new Exception("Product category with "+ productCategoryId+ " Id does not exist");
        }
        productCategoryRepository.deleteById(productCategoryId);
    }

    public ProductCategory productCategoryDetail(Integer productCategoryId) throws Exception {
        boolean isValid = idExist(productCategoryId);
        if(!isValid){
            throw new Exception("Product category with "+ productCategoryId + " Id does not exist");
        }
        return findProductCategory(productCategoryId);
    }


    public ProductCategory updateProductCategoryDetails(Integer productCategoryId, ProductCategory productCategory) throws Exception {
         productCategoryRepository.findById(productCategoryId).orElseThrow(
                ()-> new Exception(
                        "Product category with "+ productCategoryId + " Id does not exist")
        );
        ProductCategory productCategory1 = findProductCategory(productCategoryId);
        if(Objects.nonNull(productCategory.getCategoryName())&& !"".equalsIgnoreCase(productCategory.getCategoryName())){
            productCategory1.setCategoryName(productCategory.getCategoryName());
        }
        if(Objects.nonNull(productCategory.getDescription())&& !"".equalsIgnoreCase(productCategory.getDescription())){
            productCategory1.setDescription(productCategory.getDescription());
        }
        if(Objects.nonNull(productCategory.getShopId())){
            productCategory1.setShopId(productCategory.getShopId());
        }

        return productCategoryRepository.save(productCategory1);
    }

}
