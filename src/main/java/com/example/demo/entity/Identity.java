package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.sql.Date;


@Data
@Entity
@TableName("Identity")
public class Identity {
    @Id
    private String id ;
    private String name ;
    private String gender ;
    private Date  birthday;
    private String nation ;
    private String address;

}
