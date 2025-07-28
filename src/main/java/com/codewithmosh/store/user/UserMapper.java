package com.codewithmosh.store.user;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper( componentModel = "spring")
public interface UserMapper {
   List<UserDto> toDto(List<User> users);

   UserDto toDto(User user);
}
