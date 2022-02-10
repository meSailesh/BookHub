package com.teamAlpha.bookHub.catalog.repository;

import com.teamAlpha.bookHub.catalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
