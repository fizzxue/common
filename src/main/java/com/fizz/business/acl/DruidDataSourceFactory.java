package com.fizz.business.acl;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author Fizz
 * @since 2024/9/22 14:26
 */
public class DruidDataSourceFactory extends UnpooledDataSourceFactory {
    // 数据源初始化
    @Override
    public DataSource getDataSource() {
        try {
            ((DruidDataSource)this.dataSource).init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this.dataSource;
    }

    public DruidDataSourceFactory() {
        this.dataSource = new DruidDataSource();
    }
}
