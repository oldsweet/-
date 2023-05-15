package com.usc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.usc.mapper")
public class IDCApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(IDCApplicationMain.class,args);
    }
}