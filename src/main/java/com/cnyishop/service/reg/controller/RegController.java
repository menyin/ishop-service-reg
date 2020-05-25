package com.cnyishop.service.reg.controller;

import com.cnyishop.service.reg.mapper.UserMapper;
import com.cnyishop.service.reg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 采用restfull风格
 */
@RestController
@RequestMapping(value = "user")
public class RegController {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据 ID 测试查询用户信息
     *
     * @param id
     * @return
     */
    @PostMapping("")
    public String reg(User user) {
        return userMapper.insert(user) > 0 ? "注册成功" : "注册失败";
    }
    @PutMapping("")
    public String updateUser(User user) {
        return userMapper.update(user) > 0 ? "更新成功" : "更新失败";
    }
    @GetMapping("{id}")
    public User getUser(@PathVariable String id) {
        return userMapper.findById(id);
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable String id) {
        return userMapper.deleteById(id)> 0 ? "删除成功" : "删除失败";
    }

}
