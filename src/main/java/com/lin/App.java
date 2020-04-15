package com.lin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lin.mapper*")//启动类中添加这句，扫描dao文件
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
