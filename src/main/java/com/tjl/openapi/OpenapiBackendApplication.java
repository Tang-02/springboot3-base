package com.tjl.openapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Tang
 * 如需开启 Redis，须移除 exclude 中的内容
 */
//@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@SpringBootApplication
@MapperScan("com.tjl.openapi.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class OpenapiBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenapiBackendApplication.class, args);
    }

}
