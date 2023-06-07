package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@TableName("issuance")
public class Issuance {
    @Id
    private String id;
    private String type;
    private String reason;
    private LocalDate period;
    private int status = 1;
}
