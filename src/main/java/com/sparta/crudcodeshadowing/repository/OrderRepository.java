package com.sparta.crudcodeshadowing.repository;

import com.sparta.crudcodeshadowing.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
