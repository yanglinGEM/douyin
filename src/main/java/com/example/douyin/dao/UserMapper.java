package com.example.douyin.dao;

import com.example.douyin.dto.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into users(name, password) values (#{name}, #{password});")
    boolean insert(User user);

    @Select("select id, name, password from users where id = #{id};")
    User selectById(int id);

    @Select("select id, name, password from users where name = #{name};")
    User selectByName(String name);

}
