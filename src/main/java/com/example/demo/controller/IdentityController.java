package com.example.demo.controller;

import com.example.demo.dto.Search;
import com.example.demo.entity.Identity;
import com.example.demo.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/identity")
public class IdentityController {
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    Service service;

    @GetMapping("/search")
    @Operation(summary = "检索身份信息")
    public ResponseEntity<Object> getIdentity(Search search) {
        List<Identity> identities;
        if (search.getType() == null) {
            identities = service.getIdentity();
            return ResponseEntity.ok(identities);
        }
        identities = service.getIdentity(search);
        return ResponseEntity.ok().body(identities);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除身份信息")
    public ResponseEntity<Object> deleteIdentity(String id) {
        if (service.deleteIdentity(id)) {
            return ResponseEntity.ok("删除成功");
        } else {
            return ResponseEntity.status(400).body("删除失败");
        }
    }

    @PostMapping("/add")
    @Operation(summary = "添加身份信息")
    public ResponseEntity<Object> addIdentity(@RequestBody Identity identity) {
        if (service.addIdentity(identity)) {
            return ResponseEntity.ok("添加成功");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("身份证号已存在");
        }
    }

    @PutMapping("/edit")
    @Operation(summary = "编辑身份信息")
    public ResponseEntity<Object> editIdentity(@RequestBody Identity identity) {
        if (service.editIdentity(identity)) {
            return ResponseEntity.ok("编辑成功");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("未知错误");
        }
    }

    @GetMapping("/personal")
    @Operation(summary = "获取用户个人信息")
    public ResponseEntity<Object> getPersonal(String username) {
        try {
            Identity identity = service.getPerson(username);
            return ResponseEntity.ok(identity);

        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("数据异常");
        }
    }


}
