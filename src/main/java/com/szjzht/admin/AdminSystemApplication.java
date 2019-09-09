package com.szjzht.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.szjzht.admin.mapper")
public class AdminSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminSystemApplication.class, args);
    }

}
