package com.example.blogbackend.entities;

import com.example.blogbackend.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false, length = 50)
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String about;

    @Enumerated(EnumType.STRING)
    private Role role;
}
