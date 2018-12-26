package com.example.demo.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

//该类继承自 AbstractRoutingDataSource 类，在访问数据库时会调用该类的 determineCurrentLookupKey() 方法获取数据库实例的 key
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private static volatile DynamicRoutingDataSource instance; //单例
    private static byte[] lock = new byte[0];
    private Map<Object, Object> targetDataSources;

    public static synchronized DynamicRoutingDataSource getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new DynamicRoutingDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        //logger.info("Current DataSource is [{}]", DynamicDataSourceContextHolder.getDataSourceKey());
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }

    public Map<Object, Object> getTargetDataSource() {
        return targetDataSources;
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        this.targetDataSources = targetDataSources;
        super.setTargetDataSources(targetDataSources);
        this.afterPropertiesSet();
    }

}
