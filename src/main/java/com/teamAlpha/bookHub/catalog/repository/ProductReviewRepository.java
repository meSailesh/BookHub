package com.teamAlpha.bookHub.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamAlpha.bookHub.catalog.entity.ProductReview;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer>{

}
