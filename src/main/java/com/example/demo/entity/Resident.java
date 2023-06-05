package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;

@Entity
@TableName("resident")
@Data
public class Resident {
    @Id
    private String id;
    private Date period;
}
