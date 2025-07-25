package com.cupk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cupk.mapper")
public class BookManageSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookManageSystemApplication.class, args);
    }

}
