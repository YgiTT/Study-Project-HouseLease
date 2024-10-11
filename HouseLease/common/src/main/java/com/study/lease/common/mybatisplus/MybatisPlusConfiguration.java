package com.study.lease.common.mybatisplus;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Ryan Yan
 * @Since 2024/10/11 10:43
 */
@Configuration
@MapperScan("com.study.lease.web.*.mapper")
public class MybatisPlusConfiguration {
}
