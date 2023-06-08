package com.example.demo.controller;

import com.example.demo.dto.ManageMigrate;
import com.example.demo.dto.ManageResident;
import com.example.demo.dto.Total;
import com.example.demo.entity.*;
import com.example.demo.service.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            if (!identity.getId().equals("")) {
                service.addIdentity(identity);
                service.addDomicile(domicile);
                service.addResident(resident);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("服务器内部错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("服务器内部错误");
        }
        return ResponseEntity.ok("添加成功");
    }

    @GetMapping("/issuance/get")
    public Issuance getIssuance(@RequestParam String id, @RequestParam String type) {
        return service.getIssuance(id, type);
    }

    @PostMapping("/issuance/add")
    public ResponseEntity<Object> addIssuance(@RequestBody Issuance issuance) {
        if (issuance.getId() == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("请检查填写内容");
        }
        if (service.getIssuance(issuance.getId(), issuance.getType()) == null) {
            if (service.addIssuance(issuance)) {
                return ResponseEntity.ok("申请成功");
            }
        } else {
            if (service.editIssuance(issuance)) {
                return ResponseEntity.ok("修改提交");
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("未知错误");
    }

    @GetMapping("/migrate/get")
    public Migrate getMigrate(String id) {
        return service.getMigrate(id);
    }

    @PostMapping("/migrate/add")
    public ResponseEntity<Object> addMigrate(@RequestBody Migrate migrate) {
        if (migrate.getId() == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("请检查填写内容");
        }
        if (service.getMigrate(migrate.getId()) == null) {
            if (service.addMigrate(migrate)) {
                return ResponseEntity.ok("申请成功");
            }
        } else {
            if (service.editMigrate(migrate)) {
                return ResponseEntity.ok("修改提交");
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("未知错误");
    }

    @PutMapping("/migrate/edit")
    public ResponseEntity<Object> editMigrate(Migrate migrate) {
        boolean result = service.editMigrate(migrate);
        if (result) {
            return ResponseEntity.ok("编辑成功");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("未知错误");
        }
    }

    @GetMapping("/migrate/search")
    public List<ManageMigrate> searchMigrate() {
        return service.getMigrate();
    }

    @GetMapping("/resident/search")
    public List<ManageResident> searchResident() {
        return service.getResident();
    }

    @PutMapping("/migrate/handle")
    public ResponseEntity<Object> handleMigrate(@RequestBody Migrate migrate) {
        if (service.editMigrate(migrate)) {
            return ResponseEntity.ok("处理成功");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("处理失败");
        }
    }

    @PutMapping("/resident/handle")
    public ResponseEntity<Object> handleResident(@RequestBody Resident resident) {
        if (service.editResident(resident)) {
            return ResponseEntity.ok("处理成功");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("处理失败");
        }
    }
}


