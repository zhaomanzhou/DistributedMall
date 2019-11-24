package com.mmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan("com.mmall.mapper")
public class MmallApplication {


    public static void main(String[] args) {
        SpringApplication.run(MmallApplication.class, args);
    }

}
