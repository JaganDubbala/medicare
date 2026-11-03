package com.medicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicare.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
