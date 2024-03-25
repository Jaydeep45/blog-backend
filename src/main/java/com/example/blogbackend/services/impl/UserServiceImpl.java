package com.example.blogbackend.services.impl;

import com.example.blogbackend.dtos.PageResponse;
import com.example.blogbackend.dtos.UserDto;
import com.example.blogbackend.entities.User;
import com.example.blogbackend.exceptions.ResourceNotFoundException;
import com.example.blogbackend.repositories.UserRepository;
import com.example.blogbackend.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String id) {
        User user = userRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("User not found with id: "+ id));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAbout(userDto.getAbout());
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto getUser(String id) {
        return modelMapper.map(userRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("User not found with id: "+ id)), UserDto.class);
    }

    @Override
    public PageResponse<UserDto> getAllUsers(Integer pageNumber, Integer pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<User> userPage = userRepository.findAll(pageRequest);
        PageResponse<UserDto> pageResponse = new PageResponse<>();
        pageResponse.setContent(
                userPage.getContent().stream().map(
                        user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList())
        );
        pageResponse.setPage(userPage.getNumber());
        pageResponse.setSize(userPage.getSize());
        pageResponse.setTotalElements(userPage.getTotalElements());
        pageResponse.setTotalPages(userPage.getTotalPages());
        return pageResponse;
    }
    @Override
    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("User not found with id: "+ id));
        userRepository.delete(user);
    }
}
