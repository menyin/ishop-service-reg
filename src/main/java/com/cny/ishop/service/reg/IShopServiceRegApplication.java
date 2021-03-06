package com.cny.ishop.service.reg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.cny.ishop.service.reg.mapper")
@EnableSwagger2
public class IShopServiceRegApplication {
    public static void main(String[] args) {
        SpringApplication.run(IShopServiceRegApplication.class, args);
    }
}
