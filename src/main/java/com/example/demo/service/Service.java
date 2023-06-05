package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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

    public Identity getPerson(String username) {
        QueryWrapper<Bind> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        String id = bindMapper.selectOne(queryWrapper).getId();
        return identityMapper.selectById(id);
    }

    public boolean selectId(String id) {
        return identityMapper.selectById(id) != null;
    }

    public List<Identity> selectIdentity() {
        return identityMapper.selectList(null);

    }

    public List<Identity> selectIdentity(Search search) {
        String type = search.getType();
        String index = search.getIndex();
        QueryWrapper<Identity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(type, index);
        return identityMapper.selectList(queryWrapper);
    }


    public List<Domicile> selectDomicile() {
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
        migrate.setStatus(1);
        return migrateMapper.updateById(migrate) > 0;
    }

    public boolean getMigrateStatus(String id) {
        QueryWrapper<Migrate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return migrateMapper.selectById(id) != null;
    }

}