package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import sun.tools.jar.resources.jar;
import tk.mybatis.spring.annotation.MapperScan;

/*****
 * @Author: www.itheima.com
 * @Description: com.changgou
 * 启动类
 ****/
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.changgou.goods.dao"})  //Dao接口包扫描->@MapperScan:tk下的包
public class GoodsApplicatin {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplicatin.class,args);
    }
}
