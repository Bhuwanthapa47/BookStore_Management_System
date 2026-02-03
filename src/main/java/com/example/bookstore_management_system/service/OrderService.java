package com.example.bookstore_management_system.service;

import com.example.bookstore_management_system.entity.*;
import com.example.bookstore_management_system.repository.BookRepository;
import com.example.bookstore_management_system.repository.CartRepository;
import com.example.bookstore_management_system.repository.OrderRepository;
import com.example.bookstore_management_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Transactional
    public Order placeOrder(String email) {

        Cart cart = cartRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(userRepository.findByEmail(email).get());
        order.setOrderDate(LocalDateTime.now());

        double total = 0;

        for (CartItem cartItem : cart.getItems()) {
            Book book = cartItem.getBook();

            if (book.getStockQuantity() < cartItem.getQuantity()) {
                throw new RuntimeException("Insufficient stock");
            }

            book.setStockQuantity(
                    book.getStockQuantity() - cartItem.getQuantity()
            );

            OrderItem orderItem = new OrderItem();
            orderItem.setBook(book);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(book.getPrice());

            order.getItems().add(orderItem);
            total += book.getPrice() * cartItem.getQuantity();
        }

        order.setTotalAmount(total);

        cart.getItems().clear(); // empty cart after order

        return orderRepository.save(order);
    }
}

