package com.example.demo.controller;

import com.example.demo.entity.Domicile;
import com.example.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/domicile")
public class DomicileController {
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    Service service;

    @GetMapping("/search")
    public List<Domicile> getDomicile() {
        return service.selectDomicile();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addDomicile(@RequestBody Domicile domicile) {
        if (service.addDomicile(domicile)) {
            return ResponseEntity.ok("添加成功");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("身份证号已存在");
        }
    }
}
