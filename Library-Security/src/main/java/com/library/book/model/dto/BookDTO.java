package com.library.book.model.dto;


import com.library.user.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {


    private String tittle;

    private UserDto author;

    private String authorName;

    private String description;

}
