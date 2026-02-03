package com.example.bookstore_management_system.controller;

import com.example.bookstore_management_system.entity.Cart;
import com.example.bookstore_management_system.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add/{bookId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public Cart addToCart(
            @PathVariable Long bookId,
            @RequestParam int quantity,
            Authentication auth) {

        return cartService.addToCart(auth.getName(), bookId, quantity);
    }

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public Cart viewCart(Authentication auth) {
        return cartService.viewCart(auth.getName());
    }

    @DeleteMapping("/remove/{cartItemId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public Cart removeFromCart(
            @PathVariable Long cartItemId,
            Authentication authentication) {

        return cartService.removeFromCart(authentication.getName(), cartItemId);
    }


}

