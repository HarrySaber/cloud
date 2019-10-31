/**
 * Copyright (C) Kuagejing Logistics Technology Co., Ltd.
 * All Rights Reserved.
 * <p>
 * MybatisPlusInterceptorConfig.java created on 2019/8/19 by chenbo
 */
package com.springboot.cloud.common.web.config;

import com.springboot.cloud.common.web.interceptor.SqlInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 *
 * @Author chenbo
 * @Date 2019/8/19 16:24
 */
@Configuration
@Slf4j
public class MybatisPlusInterceptorConfig {
    @Bean
    public SqlInterceptor primaryInterceptor(){
        log.info("inject sql interceptor>>>>>>>>>>>>>>>");
        return new SqlInterceptor();
    }
}
