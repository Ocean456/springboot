package com.example.demo.dto;

import com.example.demo.entity.Issuance;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ManageIssuance extends Issuance {
    private String name;
    private String address;
}
