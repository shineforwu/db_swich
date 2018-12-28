package com.example.demo.aspect;

import com.example.demo.DemoApplication;
import com.example.demo.db.DataSourceCommonUtils;
import com.example.demo.db.DynamicDataSourceContextHolder;
import com.example.demo.db.MyDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Aspect
@Component
public class TestAspect {
    //@Pointcut("(execution(* com.example.demo.controller.TestController.*(..))) || execution(* org.mybatis.spring.SqlSessionTemplate.*(..))")
    @Pointcut("(execution(* com.example.demo.controller.TestController.*(..))) " + "execution(* org.mybatis.spring.SqlSessionTemplate.*(..))")
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
        if(dbName.equals("db1"))
        {
            DynamicDataSourceContextHolder.setDataSourceKey("db1");
//            String temp= DynamicDataSourceContextHolder.getDataSourceKey();
            //DataSourceCommonUtils u=new DataSourceCommonUtils();
            //u.changeDataSource(DataSourceCommonUtils.getDataSourceList().get(0));
            //DemoApplication.dataSource=DataSourceCommonUtils.getDataSource(DataSourceCommonUtils.getDataSourceList().get(0));
        }
        else
        {
            DynamicDataSourceContextHolder.setDataSourceKey("db2");
            //.dataSource=DataSourceCommonUtils.getDataSource(DataSourceCommonUtils.getDataSourceList().get(1));
        }

    }

}
