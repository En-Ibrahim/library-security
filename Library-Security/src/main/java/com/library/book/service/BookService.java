package com.library.book.service;

import com.library.book.model.Book;
import com.library.book.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book newBook) {
        return bookRepository.findById(id)
                .map(b -> {
                    b.setTitle(newBook.getTitle());
                    b.setAuthor(newBook.getAuthor());
                    b.setDescription(newBook.getDescription());
                    return bookRepository.save(b);
                })
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
