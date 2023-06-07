package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@TableName("modification")
public class Modification {
    @Id
    private String id;
    private String name;
    private String gender;
    private String nation;
    private String birthday;
    private String address;
    private int status = 1;
}
