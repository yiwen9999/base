package com.hex.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableScheduling 定时器开启设置
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
