package com.demo.plugins;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * User: lanxinghua
 * Date: 2019/10/18 09:39
 * Desc: 分页
 */
@Intercepts(@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class}
))
public class MyPlugin implements Interceptor {

    // 拦截目标对象中目标方法的执行
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }

    // 为目标对象创建一个代理对象
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置信息.....");
    }
}
