package com.example.blogbackend.controllers;

import com.example.blogbackend.dtos.ApiResponseDTO;
import com.example.blogbackend.dtos.PageResponse;
import com.example.blogbackend.dtos.UserDto;
import com.example.blogbackend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    private ResponseEntity<ApiResponseDTO<UserDto>> saveUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseDTO<>(HttpStatus.CREATED.value(), "User Created Successfully", userService.createUser(userDto)));
    }

    @GetMapping
    private ResponseEntity<ApiResponseDTO<PageResponse>> getAllUsers(
            @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(defaultValue = "10", required = false) Integer pageSize
    ) {
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpStatus.OK.value(), "Users List", userService.getAllUsers(pageNumber, pageSize)));
    }

    @GetMapping("/{id}")
    private ResponseEntity<ApiResponseDTO<UserDto>> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpStatus.OK.value(), "User Data with id: " + id, userService.getUser(id)));
    }

    @PutMapping("/{id}")
    private ResponseEntity<ApiResponseDTO<UserDto>> updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpStatus.OK.value(), "User Data updated with id: " + id,userService.updateUser(userDto, id)));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ApiResponseDTO<?>> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpStatus.OK.value(), "User deleted with id: " + id,null));
    }
}
