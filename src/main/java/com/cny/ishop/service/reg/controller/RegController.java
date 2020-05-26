package com.cny.ishop.service.reg.controller;

import com.cny.ishop.service.reg.mapper.UserMapper;
import com.cny.ishop.service.reg.model.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 采用restfull风格
 */
@ApiOperation(value = "用户服务", notes = "以实体类为参数，注意用户名和邮箱不要重复")
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
