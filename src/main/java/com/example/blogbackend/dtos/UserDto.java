package com.example.blogbackend.dtos;


import com.example.blogbackend.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements UserDetails {
    private String id;

    @NotNull(message = "User Name cannot be null")
    @Size(max = 50, min = 2, message = "Please enter a valid user name (2-50 characters")
    @NotBlank(message = "User Name cannot be empty")
    private String userName;

    @NotNull(message = "First Name cannot be null")
    @NotBlank(message = "First Name cannot be empty")
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    @NotBlank(message = "Last Name cannot be empty")
    private String lastName;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be empty")
    @Min(value = 8, message = "Password should have minimum 8 characters")
    @Max(value = 20, message = "Password should have maximum 20 characters")
    private String password;

    @Email(message = "Please enter a valid email")
    private String email;

    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    @Pattern(regexp = "\\d+", message = "Phone number must contain only digits")
    private String phoneNumber;

    @NotNull(message = "Please provide a role")
    private Role role;

    private String about;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
