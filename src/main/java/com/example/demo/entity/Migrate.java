package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@TableName("migrate")
public class Migrate {
    @Id
    private String id;
    private String address;
    private int status;
}
