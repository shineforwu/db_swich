package com.example.demo.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        //按照目标数据源名称和目标数据源对象的映射存放在Map中
        Map<Object, Object> targetDataSources = new HashMap<>();
        List<MyDataSource> list=initdb();
        DataSourceCommonUtils.setDataSource(list);
        targetDataSources.put(DataSourceCommonUtils.getDataSourceList().get(0).getDbKeyName(), DataSourceCommonUtils.getDataSource(DataSourceCommonUtils.getDataSourceList().get(0)));
        targetDataSources.put(DataSourceCommonUtils.getDataSourceList().get(1).getDbKeyName(), DataSourceCommonUtils.getDataSource(DataSourceCommonUtils.getDataSourceList().get(1)));
        //采用是想AbstractRoutingDataSource的对象包装多数据源
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        //设置默认的数据源，当拿不到数据源时，使用此配置
        dataSource.setDefaultTargetDataSource(DataSourceCommonUtils.getDataSource(DataSourceCommonUtils.getDataSourceList().get(0)));
        return dataSource;
    }
    protected  List<MyDataSource> initdb() {
        List<MyDataSource> list = new ArrayList<MyDataSource>();
        MyDataSource db=new MyDataSource();
        db.setDbKeyName("db1");
        db.setDriverClassName("com.mysql.cj.jdbc.Driver");
        db.setUserName("root");
        db.setPassword("123456");
        db.setUrl("jdbc:mysql://127.0.0.1:3306/user1?serverTimezone=GMT%2B8");
        list.add(db);
        MyDataSource db2=new MyDataSource();
        db2.setDbKeyName("db2");
        db2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        db2.setUserName("root");
        db2.setPassword("123456");
        db2.setUrl("jdbc:mysql://127.0.0.1:3306/user2?serverTimezone=GMT%2B8");
        list.add(db2);
        return list;
    }
    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }


}
