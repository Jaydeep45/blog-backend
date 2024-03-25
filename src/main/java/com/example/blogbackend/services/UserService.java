package com.example.blogbackend.services;

import com.example.blogbackend.dtos.PageResponse;
import com.example.blogbackend.dtos.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, String id);

    UserDto getUser(String id);

    PageResponse<UserDto> getAllUsers(Integer pageNumber, Integer pageSize);

    void deleteUser(String id);
}
