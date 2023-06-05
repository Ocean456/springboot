package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.entity.Bind;
import com.example.demo.entity.Identity;
import com.example.demo.entity.Search;
import com.example.demo.entity.User;
import com.example.demo.mapper.BindMapper;
import com.example.demo.mapper.IdentityMapper;
import com.example.demo.mapper.UserMapper;
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
    BindMapper bindMapper;

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
}