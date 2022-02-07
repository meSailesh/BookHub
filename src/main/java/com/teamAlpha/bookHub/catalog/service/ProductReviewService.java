package com.teamAlpha.bookHub.catalog.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamAlpha.bookHub.catalog.controller.ProductCategoryController;
import com.teamAlpha.bookHub.catalog.entity.ProductCategory;
import com.teamAlpha.bookHub.catalog.entity.ProductReview;
import com.teamAlpha.bookHub.catalog.exception.ProductCategoryNotFoundException;
import com.teamAlpha.bookHub.catalog.model.ProductCategoryDto;
import com.teamAlpha.bookHub.catalog.model.ProductReviewDto;
import com.teamAlpha.bookHub.catalog.repository.ProductReviewRepository;

@Service
public class ProductReviewService {

	@Autowired
	ProductReviewRepository productReviewRepository; 
	
	public ProductReviewDto createProductReview(ProductReview productReview) {
		
		ProductReviewDto productReviewDto = new ProductReviewDto();
		ProductReview savedProductReview = productReviewRepository.save(productReview);
		BeanUtils.copyProperties(productReviewDto, savedProductReview);
		
		

//		productCategoryDto.add(linkTo(methodOn(ProductCategoryController.class).GetProductCategory()).withRel("list"));
//		productCategoryDto.add(linkTo(
//				methodOn(ProductCategoryController.class).productCategoryDetails(productCategory.getCategoryId()))
//						.withSelfRel());
//		
		return productReviewDto;
	}

	public ProductReviewDto productReviewFindById(Integer productReviewId) throws ProductCategoryNotFoundException {
		try {
			
			ProductReviewDto productReviewDto = new ProductReviewDto(); 
			ProductReview productById = productReviewRepository.findById(productReviewId).get(); 
			BeanUtils.copyProperties(productReviewDto, productById);
		

			
//			productCategoryDto
//					.add(linkTo(methodOn(ProductCategoryController.class).GetProductCategory()).withRel("list"));
			
			return productReviewDto;

		} catch (Exception e) {
			throw new ProductCategoryNotFoundException(productReviewId);
		}
	}

	public List<ProductReviewDto> getAllProductReview() {
		
		List<ProductReviewDto> listProductReview = new ArrayList<ProductReviewDto>();
		List<ProductReview> productReviews = productReviewRepository.findAll(); 
		BeanUtils.copyProperties(listProductReview, productReviews);
		
		return listProductReview;
	}

	public String deleteProductReviewById(Integer productCategoryId) throws ProductCategoryNotFoundException {

		try {
		
			ProductReview deletedReview = productReviewRepository.findById(productCategoryId).get();
		
			productReviewRepository.deleteById(productCategoryId);
			
			return ("Successfully deleted product review: " + deletedReview.getReviewMessage());

		} catch (Exception e) {
			throw new ProductCategoryNotFoundException(productCategoryId);
		}

	}

//	public ProductReviewDto updateProductCategoryDetails(Integer productCategoryId, ProductCategory productCategory)
//			throws ProductCategoryNotFoundException {
//
//		try {
//			ProductCategory productCategory1 = productCategoryRepository.findById(productCategoryId).get();
//			ProductCategoryDto productCategoryDto = new ProductCategoryDto();
//
//			if (Objects.nonNull(productCategory.getCategoryName())
//					&& !"".equalsIgnoreCase(productCategory.getCategoryName())) {
//				productCategory1.setCategoryName(productCategory.getCategoryName());
//			}
//			if (Objects.nonNull(productCategory.getDescription())
//					&& !"".equalsIgnoreCase(productCategory.getDescription())) {
//				productCategory1.setDescription(productCategory.getDescription());
//			}
//			if (Objects.nonNull(productCategory.getShopId())) {
//				productCategory1.setShopId(productCategory.getShopId());
//			}
//
//			ProductCategory productCategory2 = productCategoryRepository.save(productCategory1);
//			BeanUtils.copyProperties(productCategory2, productCategoryDto);
//			productCategoryDto
//					.add(linkTo(methodOn(ProductCategoryController.class).GetProductCategory()).withRel("list"));
//			productCategoryDto.add(linkTo(
//					methodOn(ProductCategoryController.class).productCategoryDetails(productCategory2.getCategoryId()))
//							.withSelfRel());
//			return productCategoryDto;
//		} catch (Exception e) {
//			throw new ProductCategoryNotFoundException(productCategoryId);
//
//		}
}
