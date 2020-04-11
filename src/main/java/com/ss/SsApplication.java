package com.ss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan("com.ss.mapper")
public class SsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsApplication.class, args);
    }

}
