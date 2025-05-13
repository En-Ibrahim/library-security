package com.library.book.service;

import com.library.book.model.Book;
import com.library.book.model.dto.BookDTO;
import com.library.book.model.mapper.BookMapping;
import com.library.book.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    private BookMapping bookMapping;
    public List<BookDTO> getAllBooks() {

        return bookMapping.toDTOList(bookRepository.findAll());
    }

    public BookDTO findByTittle(String tittle){
        Optional<Book> book= bookRepository.findByTitle(tittle);

        if (book!=null && book.isPresent() &&!book.isEmpty() )
            return bookMapping.toDTO(book.get());
        else
            throw new IllegalStateException("Not found");

    }


    public BookDTO addBook(Book book) {
        Optional<Book> optional =bookRepository.findByTitle(book.getTitle());

        if (optional==null && !optional.isPresent() && optional.isEmpty() )
            return bookMapping.toDTO(bookRepository.save(optional.get()));
        else
            throw new IllegalStateException("It's already added");
    }

    public BookDTO updateBook( Book updateBook) {
        Optional<Book> book =bookRepository.findByTitle(updateBook.getTitle());
        if (book!=null && book.isPresent() &&!book.isEmpty() )
            return bookMapping.toDTO(bookRepository.save(book.get()));
       else
           throw new IllegalStateException("Not found");
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
