package com.example.bookstore_management_system.controller;

import com.example.bookstore_management_system.entity.Book;
import com.example.bookstore_management_system.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // ✅ PUBLIC – Anyone can view books
    @GetMapping
    public Page<Book> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return bookService.getAllBooks(page, size);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // 🔒 ADMIN ONLY
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Book updateBook(
            @PathVariable Long bookId,
            @RequestBody Book updatedBook) {

        return bookService.updateBook(bookId, updatedBook);
    }


    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteBook(@PathVariable Long bookId) {

        bookService.deleteBook(bookId);
        return "Book deleted successfully";
    }





}
