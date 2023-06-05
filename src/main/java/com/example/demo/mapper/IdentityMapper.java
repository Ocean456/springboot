package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Identity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IdentityMapper extends BaseMapper<Identity> {

}
