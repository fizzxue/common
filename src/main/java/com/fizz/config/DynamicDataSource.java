package com.fizz.config;

import com.fizz.utils.spring.SpringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

/**
 * 调用setDataSource(DataSource dataSource)运行时动态设置数据源，使用完后调用clear()恢复默认数据源
 *
 * @author Fizz
 * @since 2019/10/13 16:59
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<DataSource> dataSource = ThreadLocal.withInitial(() -> (DataSource) SpringUtils.getBean("defaultDataSource"));

    public static void setDataSource(DataSource dataSource) {
        DynamicDataSource.dataSource.set(dataSource);
    }

    public static DataSource getDataSource() {
        return DynamicDataSource.dataSource.get();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return null;
    }

    @Override
    protected DataSource determineTargetDataSource() {
        return getDataSource();
    }

    public static void clear() {
        DynamicDataSource.dataSource.remove();
    }
}
