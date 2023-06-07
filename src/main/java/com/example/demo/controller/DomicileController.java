package com.example.demo.controller;

import com.example.demo.dto.Search;
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
    public List<Domicile> getDomicile(Search search) {
        if (search.getType() == null) {
            return service.getDomicile();
        }
        return service.getDomicile(search);

    }

    @PostMapping("/add")
    public ResponseEntity<Object> addDomicile(@RequestBody Domicile domicile) {
        if (service.addDomicile(domicile)) {
            return ResponseEntity.ok("添加成功");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("身份证号已存在");
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> editDomicile(@RequestBody Domicile domicile) {
        if (service.editDomicile(domicile)) {
            return ResponseEntity.ok("修改成功");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("服务器内部错误");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteDomicile(String id) {
        if (service.deleteDomicile(id)) {
            return ResponseEntity.ok().body("删除成功");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("服务器内部错误");
        }
    }
}
