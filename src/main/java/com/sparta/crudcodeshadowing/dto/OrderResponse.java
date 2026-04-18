package com.sparta.crudcodeshadowing.dto;

import com.sparta.crudcodeshadowing.entity.Order;
import lombok.Getter;

@Getter
public class OrderResponse {
    private final Long orderId;
    private final ProductResponse product;

    public OrderResponse(Order order) {
        this.orderId = order.getId();
        this.product = new ProductResponse(order.getProduct());
    }
}
