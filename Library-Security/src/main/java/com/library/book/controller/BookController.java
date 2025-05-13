package com.library.book.controller;


import com.library.book.model.Book;
import com.library.book.model.dto.BookDTO;
import com.library.book.model.mapper.BookMapping;
import com.library.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private BookMapping bookMapping;
    @GetMapping
    public ResponseEntity<?> getAllBooks() {

        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<?> addBook(@RequestBody Book book) {

        return ResponseEntity.ok(bookService.addBook(book));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<?> updateBook( @RequestBody Book book) {

        return ResponseEntity.ok(bookService.updateBook( book));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
