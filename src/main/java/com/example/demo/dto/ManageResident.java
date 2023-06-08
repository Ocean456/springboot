package com.example.demo.dto;

import com.example.demo.entity.Resident;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ManageResident extends Resident {
    private String name;
    private String address;
}
