package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@TableName("domicile")
public class Domicile {
    @Column(name = "name")
    private String name;
    @Column(name = "former")
    private String former;
    @Column(name = "birthplace")
    private String birthplace;
    @Column(name = "origin")
    private String origin;
    @Column(name = "gender")
    private String gender;
    @Column(name = "nation")
    private String nation;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Id
    private String id;
    @Column(name = "education")
    private String education;
    @Column(name = "marital")
    private String marital;
    @Column(name = "career")
    private String career;
}
