package com.sparta.crudcodeshadowing.service;

import com.sparta.crudcodeshadowing.dto.OrderRequest;
import com.sparta.crudcodeshadowing.dto.OrderResponse;
import com.sparta.crudcodeshadowing.entity.Order;
import com.sparta.crudcodeshadowing.entity.Product;
import com.sparta.crudcodeshadowing.repository.OrderRepository;
import com.sparta.crudcodeshadowing.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponse createOrder(@Valid OrderRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "해당 상품이 존재하지 않습니다. id=" + request.getProductId()));
        Order order = new Order(product);
        return new OrderResponse(orderRepository.save(order));
    }

    public OrderResponse getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "해당 주문이 존재하지 않습니다. id=" + id));
        return new OrderResponse(order);
    }
}
