package com.teamAlpha.bookHub.catalog.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamAlpha.bookHub.catalog.controller.ProductReviewController;
import com.teamAlpha.bookHub.catalog.entity.Product;
import com.teamAlpha.bookHub.catalog.entity.ProductReview;
import com.teamAlpha.bookHub.catalog.exception.ProductNotFoundException;
import com.teamAlpha.bookHub.catalog.exception.ProductReviewNotFoundException;
import com.teamAlpha.bookHub.catalog.model.ProductReviewDto;
import com.teamAlpha.bookHub.catalog.repository.ProductRepository;
import com.teamAlpha.bookHub.catalog.repository.ProductReviewRepository;

@Service
public class ProductReviewService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductReviewService.class);

	@Autowired
	ProductReviewRepository productReviewRepository;

	@Autowired
	ProductRepository productRepository;

	public ProductReviewDto saveProductReview(ProductReviewDto productReviewDto) throws ProductNotFoundException {
		try {
			LOGGER.info("creating product review");

			Product product = productRepository.findById(productReviewDto.getProductId()).get();
			ProductReview newProductReview = new ProductReview();
			BeanUtils.copyProperties(product, newProductReview);
			newProductReview.setProduct(product);
			ProductReview savedProductReview = productReviewRepository.save(newProductReview);
			LOGGER.info("product review created");
			ProductReviewDto savedProductReviewDto = new ProductReviewDto();
			BeanUtils.copyProperties(savedProductReviewDto, savedProductReview);

			savedProductReviewDto
					.add(linkTo(methodOn(ProductReviewController.class).getAllProductReview()).withRel("list"));
			savedProductReviewDto.add(linkTo(
					methodOn(ProductReviewController.class).findProductReviewById(savedProductReview.getReviewId()))
							.withSelfRel());

			return savedProductReviewDto;

		} catch (Exception e) {
			LOGGER.error("Error saving product review");
			throw new ProductNotFoundException(productReviewDto.getProductId());
		}

	}

	public ProductReviewDto productReviewFindById(Integer productReviewId) throws ProductReviewNotFoundException {
		try {
			LOGGER.info("finding product review by id");
			ProductReviewDto productReviewDto = new ProductReviewDto();
			ProductReview productReview = productReviewRepository.findById(productReviewId).get();
			BeanUtils.copyProperties(productReviewDto, productReview);

			productReviewDto.add(linkTo(methodOn(ProductReviewController.class).getAllProductReview()).withRel("list"));

			return productReviewDto;

		} catch (ProductReviewNotFoundException e) {
			throw new ProductReviewNotFoundException(productReviewId);
		}
	}

	public List<ProductReviewDto> getAllProductReview() {

		List<ProductReviewDto> listProductReview = new ArrayList<ProductReviewDto>();
		List<ProductReview> productReviews = productReviewRepository.findAll();
		BeanUtils.copyProperties(listProductReview, productReviews);

		return listProductReview;
	}

	public String deleteProductReviewById(Integer productCategoryId) throws ProductReviewNotFoundException {

		try {

			ProductReview deletedReview = productReviewRepository.findById(productCategoryId).get();

			productReviewRepository.deleteById(productCategoryId);

			return ("Successfully deleted product review: " + deletedReview.getReviewMessage());

		} catch (ProductReviewNotFoundException e) {
			throw new ProductReviewNotFoundException(productCategoryId);
		}

	}

	public ProductReviewDto updateProductReview(Integer productReviewId, ProductReview productReview) throws ProductReviewNotFoundException {

		try {
			
			ProductReview proReview = productReviewRepository.findById(productReviewId).get();
			ProductReviewDto updatedProductReviewDto = new ProductReviewDto();
			ProductReview updatedProductReview = productReviewRepository.save(productReview);
			BeanUtils.copyProperties(updatedProductReviewDto, updatedProductReview);
			
			updatedProductReviewDto
			.add(linkTo(methodOn(ProductReviewController.class).getAllProductReview()).withRel("list"));
			updatedProductReviewDto.add(linkTo(
			methodOn(ProductReviewController.class).findProductReviewById(updatedProductReview.getReviewId()))
					.withSelfRel());
		
			return updatedProductReviewDto;
		} catch (ProductReviewNotFoundException e) {
			throw new ProductReviewNotFoundException(productReviewId);

		}
	}
}
