/**
 * Copyright (C) Kuagejing Logistics Technology Co., Ltd.
 * All Rights Reserved.
 * <p>
 * SqlInterceptor.java created on 2019/8/19 by chenbo
 */
package com.springboot.cloud.common.web.interceptor;

import com.baomidou.mybatisplus.annotation.TableId;
import com.springboot.cloud.common.core.util.DateUtils;
import com.springboot.cloud.common.core.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @Description:
 * 自定义 Mybatis 插件，自动设置 主键id的值。
 * @Author chenbo
 * @Date 2019/8/19 15:57
 */
@Slf4j
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class SqlInterceptor implements Interceptor {
    private final static String UPDATE_TIME = "updateTime";
    private final static String CREATE_TIME = "createTime";
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("sql intercept>>>>>>>>>>>>>>>>>");
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        /**
         * 获取 SQL 命令
         */
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        /**
         * 获取参数
         */
        Object parameter = invocation.getArgs()[1];
        /**
         * 获取私有成员变量
         */
        final Class<?> supperClazz = parameter.getClass();
        Field[] declaredFields = supperClazz.getSuperclass().getDeclaredFields();

        if(declaredFields == null || declaredFields.length == 0){
            declaredFields = supperClazz.getDeclaredFields();
        }

        if(SqlCommandType.INSERT.equals(sqlCommandType)){
            for (Field field : declaredFields) {
                if(CREATE_TIME.equals(field.getName())
                        || UPDATE_TIME.equals(field.getName())
                        || field.getAnnotation(TableId.class) != null){
                    field.setAccessible(true);
                }
                if(CREATE_TIME.equals(field.getName())
                        && field.get(parameter) == null){
                    field.set(parameter, DateUtils.currentDay());
                }
                if(UPDATE_TIME.equals(field.getName())
                        && field.get(parameter) == null){
                    field.set(parameter, DateUtils.currentDay());
                }
                if (field.getAnnotation(TableId.class) != null
                        && field.get(parameter) == null) {
                    field.set(parameter, IdGenerator.getId());
                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
