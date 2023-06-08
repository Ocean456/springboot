package com.example.demo.mapper;

import com.example.demo.dto.ManageMigrate;
import com.example.demo.dto.ManageResident;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExtendMapper {
    @Select("select i.id,i.name,i.address as oldAddress,m.*" + "from identity i join migrate m on i.id = m.id")
    List<ManageMigrate> selectMigrate();

    @Select("select i.id,i.name,i.address,r.*" + "from identity i join resident r on i.id = r.id")
    List<ManageResident> selectResident();
}
