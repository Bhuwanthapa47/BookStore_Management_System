package com.example.bookstore_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String genre;

    @Column(unique = true)
    private String isbn;

    private Double price;

    @Column(length = 1000)
    private String description;

    private Integer stockQuantity;

    private String imageUrl;
}
