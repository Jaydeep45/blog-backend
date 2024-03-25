package com.example.blogbackend.exceptions;

public class ResourceNotFoundException extends RuntimeException  {

    public ResourceNotFoundException(String message) {
        super(message);
    }


}
