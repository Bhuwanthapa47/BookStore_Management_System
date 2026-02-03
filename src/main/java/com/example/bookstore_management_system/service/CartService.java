package com.example.bookstore_management_system.service;

import com.example.bookstore_management_system.entity.Book;
import com.example.bookstore_management_system.entity.Cart;
import com.example.bookstore_management_system.entity.CartItem;
import com.example.bookstore_management_system.repository.BookRepository;
import com.example.bookstore_management_system.repository.CartRepository;
import com.example.bookstore_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public Cart addToCart(String email, Long bookId, int quantity) {

        Cart cart = cartRepository.findByUserEmail(email)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(userRepository.findByEmail(email).get());
                    return cartRepository.save(newCart);
                });

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        CartItem item = new CartItem();
        item.setBook(book);
        item.setQuantity(quantity);
        item.setCart(cart);

        cart.getItems().add(item);
        return cartRepository.save(cart);
    }

    public Cart viewCart(String email) {
        return cartRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

}

