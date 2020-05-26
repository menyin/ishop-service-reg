package com.cny.ishop.service.reg.mapper;

import com.cny.ishop.service.reg.model.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE ID = #{id}")
    User findById(@Param("id") String id);
    @Select("SELECT * FROM USERS WHERE NAME = #{name}")
    User findByName(@Param("name") String name);
    @Insert("INSERT INTO USERS(ID,NAME, AGE) VALUES(#{id},#{name}, #{age})")
    int insert(User user);
    @Delete("DELETE FROM USERS WHERE ID = #{id}")
    int deleteById(@Param("id") String id);
    @Update("UPDATE USERS SET NAME=#{name}, AGE=#{age} WHERE ID=#{id}")
    int update(User user);
}
