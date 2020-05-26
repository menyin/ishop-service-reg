package com.cny.ishop.service.reg.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cny.ishop.service.reg.mapper.UserMapper;
import com.cny.ishop.service.reg.model.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientException;

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
    @ApiOperation(value = "用户注册", notes="根据提交信息注册用户",httpMethod="POST")
   /* //如果参数是实体类，则可以在实体类里用@ApiModel做统一注解
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query", dataType = "Integer",example = "1"),
            @ApiImplicitParam(name = "name", value = "用户名", required = true, paramType = "query", dataType = "String",example = "马云"),
            @ApiImplicitParam(name = "age", value = "年龄", required = false, paramType = "query", dataType = "Integer",example = "18")
    })*/
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=200,message="注册成功"),
    })
    @PostMapping("")
    public String reg(User user) {
        User u=userMapper.findById(user.getId());
        if (u != null) {
            return "用户已存在";
        }else{
            return userMapper.insert(user) > 0 ? "注册成功" : "注册失败";
        }
    }
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=200,message="更新成功"),
    })
    @PutMapping("")
    public String updateUser(User user) {
        return userMapper.update(user) > 0 ? "更新成功" : "更新失败";
    }
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=200,message="获取成功"),
    })
    /**
     * 此处注意：id为@PathVariable类型参数，对应设置 paramType = "path"。而且一般不设置dataType = "Integer"，否则在线文档不能测试
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "path",example = "1"),
    })
    @SentinelResource(value = "getUser", blockHandler = "getUserQpsException")//如果不加注解则默认的vlue就是路径"/getSentinel",记得要加"/"
    @GetMapping("{id}")
    public User getUser(@PathVariable Integer id) {
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
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=200,message="删除成功"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query", dataType = "Integer",example = "1"),
    })
    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable String id) {
        return userMapper.deleteById(id)> 0 ? "删除成功" : "删除失败";
    }

}
