package com.example.demo.controller;


import com.example.demo.entity.Migrate;
import com.example.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/migrate")
public class MigrateController {
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    Service service;

    @GetMapping("/get")
    public ResponseEntity<Object> getMigrateStatus(String id) {
        boolean result = service.getMigrateStatus(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addMigrate(Migrate migrate) {
        boolean result = service.addMigrate(migrate);
        if (result) {
            return ResponseEntity.ok("添加成功");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("未知错误");
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> editMigrate(Migrate migrate) {
        boolean result = service.editMigrate(migrate);
        if (result) {
            return ResponseEntity.ok("编辑成功");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("未知错误");
        }
    }
}
