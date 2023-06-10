package com.example.demo.controller;

import com.example.demo.dto.LoginForm;
import com.example.demo.dto.ModifyForm;
import com.example.demo.dto.Register;
import com.example.demo.entity.Bind;
import com.example.demo.entity.User;
import com.example.demo.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    Service service;

    @PostMapping("/api/login")
    @Operation(summary = "登录")
    public ResponseEntity<Object> login(@RequestBody LoginForm loginForm) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        User user = service.login(username, password);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("密码错误");
        }
    }


    @PostMapping("/api/register")
    @Operation(summary = "注册")
    public ResponseEntity<Object> register(@RequestBody Register register) {
        User user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(register.getPassword());
        user.setAuthority(register.getAuthority());
        Bind bind = new Bind();
        bind.setUsername(register.getUsername());
        bind.setId(register.getId());
        if (service.queryUser(user)) {
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body("用户名已存在");
        } else {
            if (service.getIdentity(bind.getId()) == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT.value()).body("身份证号错误");
            }
            service.register(user, bind);
            return ResponseEntity.ok("注册成功");
        }
    }

    @PutMapping("/api/edit")
    @Operation(summary = "修改密码")
    public ResponseEntity<Object> modifyPassword(@RequestBody ModifyForm modifyForm) {
        if (service.editUser(modifyForm)) {
            return ResponseEntity.ok("修改成功");
        } else return ResponseEntity.status(HttpStatus.CONFLICT).body("密码错误");
    }

}
