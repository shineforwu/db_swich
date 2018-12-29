package com.example.demo.db;

import javax.sql.DataSource;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSourceCommonUtils {



    public static  List<MyDataSource> getDataSourceList() {
        return dataSourcelist;
    }

    public static void setDataSourceList(List<MyDataSource> list) {
        dataSourcelist = list;
    }

    private  static List<MyDataSource> dataSourcelist;

    public static DataSource getDataSource(MyDataSource myDataSource)
    {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(myDataSource.getDriverClassName());
        dataSource.setUsername(myDataSource.getUserName());
        dataSource.setPassword(myDataSource.getPassword());
        dataSource.setUrl(myDataSource.getUrl());
        //configuration
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(2);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(60000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(30000);
        dataSource.setValidationQuery("select 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return dataSource;
    }
    public static Map<Object, Object> setDataSource() {
        Map<Object, Object> newMap = new HashMap<>();
        if(dataSourcelist  != null && dataSourcelist.size()>0)
        {
            for (MyDataSource myDataSource : dataSourcelist) {
                DataSource dataSource = getDataSource(myDataSource);
                newMap.put(myDataSource.getDbKeyName(), dataSource);
            }
        }

        return newMap;
    }
    public static Map<Object, Object> setDataSource(List<MyDataSource> list) {
        Map<Object, Object> newMap = new HashMap<>();

        for (MyDataSource myDataSource : list) {
            DataSource dataSource = getDataSource(myDataSource);
            newMap.put(myDataSource.getDbKeyName(), dataSource);
        }
        dataSourcelist = list;
        return newMap;
    }


    public static void connectDatabase(List<MyDataSource> list) {

        try {
            for (MyDataSource myDataSource : list) {
//                String dbUrl = myDataSource.getUrl();
//                String url = dbUrl.substring(0, dbUrl.lastIndexOf("/") + 1);
//                String suffix = dbUrl.substring(dbUrl.indexOf("?"), dbUrl.length());
                Connection iotConn = DriverManager.getConnection(myDataSource.getUrl(), myDataSource.getUserName(), myDataSource.getPassword());
            }
        } catch (SQLException e) {
            System.out.print("connectDatabase:数据库不存在");
        }

    }


}
