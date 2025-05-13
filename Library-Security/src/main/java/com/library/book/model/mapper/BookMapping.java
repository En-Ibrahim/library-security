package com.library.book.model.mapper;

import com.library.book.model.Book;
import com.library.book.model.dto.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface BookMapping {

    @Mapping(source = "authorName",target = "author.name")
    Book toEntity(BookDTO bookDTO);

    @Mapping(target = "author",ignore = true)
    @Mapping(target = "authorName",source = "author.name")
    BookDTO toDTO(Book book);

    List<BookDTO> toDTOList(List<Book> books);

    List<Book> toEntityList(List<BookDTO> dtos);
}
