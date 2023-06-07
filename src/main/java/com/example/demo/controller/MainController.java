package com.example.demo.controller;

import com.example.demo.entity.Bind;
import com.example.demo.dto.LoginForm;
import com.example.demo.dto.Register;
import com.example.demo.entity.User;
import com.example.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    Service service;

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody LoginForm loginForm) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        User user = service.login(username, password);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("登录失败");
        }
    }

    @GetMapping("/")
    public String hello() {
        return "The Springboot is running";
    }

    @PostMapping("/api/register")
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
            if (service.getIdentity(bind.getId()) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT.value()).body("身份证号错误");
            }
            service.register(user, bind);
            return ResponseEntity.ok("注册成功");
        }
    }
}
