package com.cnyishop.service.reg.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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
    @SentinelResource(value = "getUser", blockHandler = "getUserQpsException")//如果不加注解则默认的vlue就是路径"/getSentinel",记得要加"/"
    @GetMapping("{id}")
    public User getUser(@PathVariable String id) {
        return userMapper.findById(id);
    }

    /**
     * 其实和dubbo的mock差不多一个意思
     * 特别注意：1、该函数的传参必须与资源点的传参一样，并且最后加上BlockException异常参数；同时，返回类型也必须一样。
     *          2、如果不加该处理函数，则默认会直接给用户抛出“No message available...”异常
     * @param e
     * @return
     */
    public User getUserQpsException(String id,BlockException e){
        System.out.println("cny输出错误："+e.getMessage());
        return new User(0,"未知用户",1000);
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable String id) {
        return userMapper.deleteById(id)> 0 ? "删除成功" : "删除失败";
    }

}
