package com.jxd.orderingsys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

/**
 * @ClassName OrderingSysApplication
 * @Description TODO
 * @Author 胡日成
 * @Date 2023/10/8 18:24
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.jxd.orderingsys.dao")
public class OrderingSysApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderingSysApplication.class,args);
    }
}
