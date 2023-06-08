package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.dto.ManageIssuance;
import com.example.demo.dto.ManageMigrate;
import com.example.demo.dto.ManageResident;
import com.example.demo.dto.Search;
import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@org.springframework.stereotype.Service
public class Service {
    @Autowired
    UserMapper userMapper;

    @Autowired
    IdentityMapper identityMapper;

    @Autowired
    DomicileMapper domicileMapper;

    @Autowired
    BindMapper bindMapper;

    @Autowired
    MigrateMapper migrateMapper;

    @Autowired
    ResidentMapper residentMapper;


    @Autowired
    IssuanceMapper issuanceMapper;

    @Autowired
    ExtendMapper extendMapper;

    public User login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("password", password);
        return userMapper.selectOne(queryWrapper);
    }


    public boolean deleteIdentity(String id) {
        return identityMapper.deleteById(id) > 0;
    }

    public boolean addIdentity(Identity identity) {
        return identityMapper.insert(identity) > 0;
    }

    public void register(User user, Bind bind) {
        userMapper.insert(user);
        bindMapper.insert(bind);
    }


    public boolean editIdentity(Identity identity) {
        UpdateWrapper<Identity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", identity.getId());
        return identityMapper.updateById(identity) > 0;
    }

    public boolean queryUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        return userMapper.selectOne(queryWrapper) != null;
    }

    public Identity getPerson(String username) throws NullPointerException {
        QueryWrapper<Bind> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        String id = bindMapper.selectOne(queryWrapper).getId();
        return identityMapper.selectById(id);
    }

    public Identity getIdentity(String id) {
        return identityMapper.selectById(id);
    }

    public List<Identity> getIdentity() {
        return identityMapper.selectList(null);

    }

    public List<Identity> getIdentity(Search search) {
        QueryWrapper<Identity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(search.getType(), search.getIndex());
        return identityMapper.selectList(queryWrapper);
    }


    public Domicile getDomicile(String id) {
        return domicileMapper.selectById(id);
    }

    public List<Domicile> getDomicile() {
        return domicileMapper.selectList(null);
    }

    public boolean addDomicile(Domicile domicile) {
        return domicileMapper.insert(domicile) > 0;
    }

    public boolean addMigrate(Migrate migrate) {
        migrate.setStatus(1);
        return migrateMapper.insert(migrate) > 0;
    }

    public boolean editMigrate(Migrate migrate) {
        UpdateWrapper<Migrate> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", migrate.getId());
        return migrateMapper.updateById(migrate) > 0;
    }

    public Migrate getMigrate(String id) {
        return migrateMapper.selectById(id);
    }

    public boolean editDomicile(Domicile domicile) {
        return domicileMapper.updateById(domicile) > 0;
    }

    public List<Domicile> getDomicile(Search search) {
        QueryWrapper<Domicile> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(search.getType(), search.getIndex());
        return domicileMapper.selectList(queryWrapper);
    }

    public Resident getResident(String id) {
        return residentMapper.selectById(id);
    }

    public boolean deleteDomicile(String id) {
        return domicileMapper.deleteById(id) > 0;
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean addResident(Resident resident) {
        return residentMapper.insert(resident) > 0;
    }


    public Issuance getIssuance(String id, String type) {
        QueryWrapper<Issuance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("type", type);
        return issuanceMapper.selectOne(queryWrapper);
    }

    public boolean addIssuance(Issuance issuance) {
        return issuanceMapper.insert(issuance) > 0;
    }

    public boolean editIssuance(Issuance issuance) {
        UpdateWrapper<Issuance> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", issuance.getId()).eq("type", issuance.getType());
        return issuanceMapper.update(issuance, updateWrapper) > 0;
    }

    public List<ManageMigrate> getMigrate() {
        return extendMapper.selectMigrate();
    }

    public List<ManageResident> getResident() {
        return extendMapper.selectResident();
    }



    public List<ManageIssuance> getIssuance() {
        return extendMapper.selectIssuance();
    }

    public boolean setAddress(String id, String address) {
        Identity identity = identityMapper.selectById(id);
        identity.setAddress(address);
        return identityMapper.updateById(identity) > 0;
    }

    public boolean addResident(Issuance issuance) {
        Resident resident = new Resident();
        resident.setId(issuance.getId());
        resident.setPeriod(issuance.getPeriod());
        return residentMapper.insert(resident) > 0;
    }

    public boolean deleteResident(String id) {
        return residentMapper.deleteById(id) > 0;
    }

    public boolean deleteIssuance(String id, String type) {
        UpdateWrapper<Issuance> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).eq("type", type);
        return issuanceMapper.delete(updateWrapper) > 0;
    }
}