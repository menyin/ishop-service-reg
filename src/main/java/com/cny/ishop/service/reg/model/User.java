package com.cny.ishop.service.reg.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户信息", description="存储用户信息")
public class User {
    @ApiModelProperty(value="编号",position = 0,required = true, dataType = "Integer",example = "1")
    private Integer id;
    @ApiModelProperty(value="用户名",position = 1,required = true,example = "马云")
    private String name;
    @ApiModelProperty(value="年龄",position = 2, dataType = "Integer",example = "18")
    private Integer age;

    public User(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
