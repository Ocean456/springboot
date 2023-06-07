package com.example.demo.controller;

import com.example.demo.dto.Total;
import com.example.demo.entity.Domicile;
import com.example.demo.entity.Identity;
import com.example.demo.entity.Resident;
import com.example.demo.service.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExtendController {
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    Service service;

    @GetMapping("/all/search")
    public Total getAll(String id) {
        Identity identity = service.getIdentity(id);
        Domicile domicile = service.getDomicile(id);
        Resident resident = service.getResident(id);
        Total total = new Total();
        if (identity != null) {
            BeanUtils.copyProperties(identity, total);
        }
        if (domicile != null) {
            BeanUtils.copyProperties(domicile, total);
        }
        if (resident != null) {
            BeanUtils.copyProperties(resident, total);
        }
        return total;
    }

    @PostMapping("/all/add")
    public ResponseEntity<Object> addAll(@RequestBody Total total) {
        try {
            Identity identity = new Identity();
            Domicile domicile = new Domicile();
            Resident resident = new Resident();
            BeanUtils.copyProperties(total, identity);
            BeanUtils.copyProperties(total, domicile);
            BeanUtils.copyProperties(total, resident);
            service.addIdentity(identity);
            service.addDomicile(domicile);
            service.addResident(resident);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("服务器内部错误");
        }
        return ResponseEntity.ok("添加成功");
    }

}
