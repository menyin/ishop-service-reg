package com.cnyishop.service.reg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
//@MapperScan(basePackages = "com.cny.ishop.commons.mapper")
@MapperScan("com.cnyishop.service.reg.mapper")
public class IShopServiceRegApplication {
    public static void main(String[] args) {
        SpringApplication.run(IShopServiceRegApplication.class, args);
    }
}
