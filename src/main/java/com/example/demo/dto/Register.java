package com.example.demo.dto;

import lombok.Data;

@Data
public class Register {
    private String id;
    private String username;
    private String password;
    private final int authority = 0;
}
