package com.example.expand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class ExpandApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpandApplication.class, args);
    }

}
