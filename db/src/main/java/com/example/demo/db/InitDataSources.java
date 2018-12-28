package com.example.demo.db;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class InitDataSources implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        DynamicRoutingDataSource dynamicRoutingDataSource=DynamicRoutingDataSource.getInstance();
//        List<MyDataSource> list = initdb();
//        Map<Object, Object> newMap = DataSourceCommonUtils.setDataSource(list);
//        DataSourceCommonUtils.connectDatabase(list);
//        DataSourceCommonUtils.setDataSource(list);
//        dynamicRoutingDataSource.setTargetDataSources(newMap);
//        DataSourceCommonUtils u=new DataSourceCommonUtils();
//        u.changeDataSource(list.get(1));
    }
    protected List<MyDataSource> initdb()
    {
        List<MyDataSource> list = new ArrayList<MyDataSource>();
        MyDataSource db=new MyDataSource();
        db.setDbKeyName("db1");
        db.setDriverClassName("com.mysql.cj.jdbc.Driver");
        db.setUserName("root");
        db.setPassword("123456");
        db.setUrl("jdbc:mysql://127.0.0.1:3306/user1?serverTimezone=GMT%2B8");
        list.add(db);
        db=new MyDataSource();
        db.setDbKeyName("db2");
        db.setDriverClassName("com.mysql.cj.jdbc.Driver");
        db.setUserName("root");
        db.setPassword("123456");
        db.setUrl("jdbc:mysql://127.0.0.1:3306/user2?serverTimezone=GMT%2B8");
        list.add(db);
        return  list;
    }
}
