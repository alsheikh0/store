package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper( componentModel = "spring")
public interface UserMapper {
   List<UserDto> toDto(List<User> users);

   UserDto toDto(User user);
}
