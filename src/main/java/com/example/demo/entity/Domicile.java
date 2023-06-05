package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@TableName("domicile")
public class Domicile {
    private String name;
    private String former_name;
    private String birthplace;
    private String gender;
    private String nation;
    private String birthday;
    @Id
    private String id;
    private String education;
    private String marital_status;
    private String career;
}
