package com.example.demo.controller;

import com.example.demo.dto.ManageIssuance;
import com.example.demo.dto.ManageMigrate;
import com.example.demo.dto.ManageResident;
import com.example.demo.dto.Total;
import com.example.demo.entity.*;
import com.example.demo.service.Service;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "信息查询")
    public ResponseEntity<Object> getAll(String id) {
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
        return ResponseEntity.ok().body(total);
    }

    @PostMapping("/all/add")
    @Operation(summary = "人口登记")
    public ResponseEntity<Object> addAll(@RequestBody Total total) {
        try {
            Identity identity = new Identity();
            Domicile domicile = new Domicile();
            BeanUtils.copyProperties(total, identity);
            BeanUtils.copyProperties(total, domicile);
            if (service.getIdentity(identity.getId()) != null)
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("身份证号已存在");
            if (!identity.getId().equals("")) {
                service.addIdentity(identity);
                service.addDomicile(domicile);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("请检查输入");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("服务器内部错误");
        }
        return ResponseEntity.ok("添加成功");
    }

    @GetMapping("/issuance/get")
    @Operation(summary = "获取个人证件信息")
    public ResponseEntity<Object> getIssuance(@RequestParam String id, @RequestParam String type) {
        Issuance issuance = service.getIssuance(id, type);
        return ResponseEntity.ok().body(issuance);
    }

    @PostMapping("/issuance/add")
    @Operation(summary = "个人申请证件")
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
    @Operation(summary = "获取个人迁移信息")
    public ResponseEntity<Object> getMigrate(String id) {
        Migrate migrate = service.getMigrate(id);
        return ResponseEntity.ok().body(migrate);
    }

    @PostMapping("/migrate/add")
    @Operation(summary = "个人申请迁移")
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
    @Operation(summary = "编辑迁移信息")
    public ResponseEntity<Object> editMigrate(Migrate migrate) {
        boolean result = service.editMigrate(migrate);
        if (result) {
            return ResponseEntity.ok("编辑成功");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("未知错误");
        }
    }

    @GetMapping("/migrate/search")
    @Operation(summary = "获取迁移管理数据")
    public ResponseEntity<Object> searchMigrate() {
        List<ManageMigrate> manageMigrates = service.getMigrate();
        return ResponseEntity.ok().body(manageMigrates);
    }

    @GetMapping("/resident/search")
    @Operation(summary = "获取居住证明数据")
    public ResponseEntity<Object> searchResident() {
        List<ManageResident> manageResidents = service.getResident();
        return ResponseEntity.ok().body(manageResidents);
    }

    @PutMapping("/migrate/handle")
    @Operation(summary = "处理迁移申请")
    public ResponseEntity<Object> handleMigrate(@RequestBody Migrate migrate) {
        if (service.editMigrate(migrate) && service.setAddress(migrate.getId(), migrate.getAddress())) {
            return ResponseEntity.ok("处理成功");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("处理失败");
        }
    }

    @GetMapping("/issuance/search")
    @Operation(summary = "获取证件发放数据")
    public ResponseEntity<Object> searchIssuance() {
        List<ManageIssuance> issuance = service.getIssuance();
        return ResponseEntity.ok().body(issuance);
    }

    @PutMapping("/issuance/handle")
    @Operation(summary = "处理证件发放")
    public ResponseEntity<Object> handleIssuance(@RequestBody Issuance issuance) {
        if (issuance.getType().equals("resident") && issuance.getStatus() == 0) {
            if (service.addResident(issuance)) {
                ResponseEntity.ok("处理成功");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("处理失败");
            }
        }
        if (service.editIssuance(issuance)) {
            return ResponseEntity.ok("处理成功");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("未知错误");
        }
    }

    @DeleteMapping("/resident/delete")
    @Operation(summary = "删除居住证明数据")
    public ResponseEntity<Object> deleteResident(String id) {
        if (service.deleteResident(id)) {
            return ResponseEntity.ok("删除成功");
        } else return ResponseEntity.status(HttpStatus.CONFLICT).body("删除失败");
    }

    @DeleteMapping("/issuance/delete")
    @Operation(summary = "删除证件发放数据")
    public ResponseEntity<Object> deleteIssuance(String id, String type) {
        if (service.deleteIssuance(id, type)) {
            return ResponseEntity.ok("删除成功");
        } else return ResponseEntity.status(HttpStatus.CONFLICT).body("删除失败");
    }

    @DeleteMapping("/migrate/delete")
    @Operation(summary = "删除迁移管理数据")
    public ResponseEntity<Object> deleteMigrate(@RequestParam String id) {
        if (service.deleteMigrate(id)) {
            return ResponseEntity.ok("删除成功");
        } else return ResponseEntity.status(HttpStatus.CONFLICT).body("删除失败");
    }
}


