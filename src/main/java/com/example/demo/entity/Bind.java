package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@TableName("bind")
@Entity
public class Bind {
    @Id
    private String username;
    private String id;
}
