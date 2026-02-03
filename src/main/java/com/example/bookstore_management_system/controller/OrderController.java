package com.example.bookstore_management_system.controller;

import com.example.bookstore_management_system.entity.Order;
import com.example.bookstore_management_system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place")
    @PreAuthorize("hasRole('CUSTOMER')")
    public Order placeOrder(Authentication authentication) {
        return orderService.placeOrder(authentication.getName());
    }
}
