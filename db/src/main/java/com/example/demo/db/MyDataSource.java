package com.example.demo.db;
import com.alibaba.druid.pool.DruidDataSource;

public class MyDataSource {
    public String getDbKeyName() {
        return dbKeyName;
    }

    public void setDbKeyName(String dbKeyName) {
        this.dbKeyName = dbKeyName;
    }

    private String dbKeyName;
    private  String driverClassName;
    private  String userName;
    private  String password;
    private  String url;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}

