package com.library.user.model.mapper;

import com.library.user.model.User;
import com.library.user.model.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User toEntity(UserDto dto);

    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> users);

    List<User> toEntityList(List<UserDto> dtos);

}
