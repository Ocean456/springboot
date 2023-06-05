package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Register {
    @Id
    private String id;
    private String username;
    private String password;
    private final int authority = 0;
}
