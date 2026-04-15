package com.sparta.crudcodeshadowing.repository;

import com.sparta.crudcodeshadowing.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
