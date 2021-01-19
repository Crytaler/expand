package com.example.expand;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@MapperScan(basePackages = {"com.example.expand.quartz.mapper"})
public class ExpandApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpandApplication.class, args);
    }

}
