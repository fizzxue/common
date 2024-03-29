package com.fizz.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author Fizz
 * @since 2019/10/14 10:50
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource defaultDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

//    @Bean
//    @Primary
//    @DependsOn({"springUtils", "defaultDataSource"})
//    public DynamicDataSource dataSource() {
//        DynamicDataSource dynamicDataSource = new DynamicDataSource();
//        dynamicDataSource.setTargetDataSources(DynamicDataSource.dataSourcesMap);
//        return dynamicDataSource;
//    }

//    @Bean
//    @ConfigurationProperties("spring.datasource.ds1")
//    public DataSource dataSource() {
//        return DruidDataSourceBuilder.create().build();
//    }
}
