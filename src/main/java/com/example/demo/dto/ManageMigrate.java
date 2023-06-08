package com.example.demo.dto;

import com.example.demo.entity.Migrate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ManageMigrate extends Migrate {
    private String name;
    private String oldAddress;
}
