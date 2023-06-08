package com.example.demo.controller;

import com.example.demo.dto.Search;
import com.example.demo.entity.Identity;
import com.example.demo.entity.Modification;
import com.example.demo.service.Service;
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
    public List<Identity> getIdentity(Search search) {
        if (search.getType() == null) {
            return service.getIdentity();
        }
        return service.getIdentity(search);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteIdentity(String id) {
        if (service.deleteIdentity(id)) {
            return ResponseEntity.ok("删除成功");
        } else {
            return ResponseEntity.status(400).body("删除失败");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addIdentity(@RequestBody Identity identity) {
        if (service.addIdentity(identity)) {
            return ResponseEntity.ok("添加成功");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("身份证号已存在");
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> editIdentity(@RequestBody Identity identity) {
        if (service.editIdentity(identity)) {
            return ResponseEntity.ok("编辑成功");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("未知错误");
        }
    }

    @GetMapping("/personal")
    public ResponseEntity<Object> getPersonal(String username) {
        try {
            Identity identity = service.getPerson(username);
            return ResponseEntity.ok(identity);

        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("数据异常");
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<Object> submitIdentity(@RequestBody Modification modification) {
        if (service.getModification(modification.getId()) == null) {
            if (service.submitModification(modification)) {
                return ResponseEntity.ok("提交成功");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("服务器内部错误");
            }
        } else {
            if (service.editModification(modification)) {
                return ResponseEntity.ok("修改申请成功");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("服务器内部错误");
            }
        }
    }

    @GetMapping("/get")
    public Modification getModification(String id) {
        return service.getModification(id);
    }
}
