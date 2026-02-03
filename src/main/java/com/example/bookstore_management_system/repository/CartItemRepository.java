package com.example.bookstore_management_system.repository;

import com.example.bookstore_management_system.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
