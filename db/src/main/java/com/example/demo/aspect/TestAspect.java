package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class TestAspect {
    @Pointcut("(execution(* com.example.demo.controller.TestController.*(..)))")
    public void sqlSource() {
    }
    @Before("sqlSource()")
    public void beforeSwitchDS(JoinPoint point) {
        // 切换数据源

        //get Http Heads
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String dbName=request.getHeader("dbName");
        if(dbName==null ||dbName.isEmpty())
        {
            return;
        }
        if(dbName.equals("User1"))
        {
           //DataSourceContextHolder.setDB("User1");
            int a=1;
        }
        else
        {
            //DataSourceContextHolder.setDB("User2");
        }

    }

}
