package com.sparta.crudcodeshadowing.service;

import com.sparta.crudcodeshadowing.dto.ProductRequest;
import com.sparta.crudcodeshadowing.dto.ProductResponse;
import com.sparta.crudcodeshadowing.entity.Product;
import com.sparta.crudcodeshadowing.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse createProduct(@Valid ProductRequest request) {
        Product product = new Product(request.getName(), request.getPrice());
        return new ProductResponse(productRepository.save(product));
    }

    public ProductResponse getProduct(Long id) {
        return new ProductResponse(findProductById(id));
    }

    public List<ProductResponse> getProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponse::new)
                .toList();
    }

    @Transactional
    public ProductResponse updateProduct(Long id, @Valid ProductRequest request) {
        Product product = findProductById(id);
        product.update(request.getName(), request.getPrice());
        return new ProductResponse(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = findProductById(id);
        productRepository.delete(product);
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
    }
}