package com.fizz.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.fizz.utils.spring.SpringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>使用步骤：</p>
 * <blockquote><pre>
 *     DynamicDataSource.dataSourcesMap.put(dataSourceKey, druidDataSource);
 *     或 DataSource.addDataSource(record);
 *     DynamicDataSource.setDataSource(dataSourceKey);
 *     调用业务代码</i>
 *     DynamicDataSource.clearKey();
 * </pre></blockquote>
 * v1.1修改：加入@DynamicDataSource注解，业务方法第一个参数必须为com.yaoshun.basic.model.DataSource
 * @author Fizz
 * @since 2019/10/14 10:49
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    public static final String DEFAULT_DS_KEY = "defaultDataSource";

    private static final ThreadLocal<String> dataSourceKey = ThreadLocal.withInitial(() -> DEFAULT_DS_KEY);

    public static Map<Object, Object> dataSourcesMap = new ConcurrentHashMap<>(10);

    static {
        dataSourcesMap.put(DEFAULT_DS_KEY, SpringUtils.getBean("defaultDataSource"));
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSource.dataSourceKey.get();
    }

    public static DruidDataSource getDSById(String id) {
        return (DruidDataSource) dataSourcesMap.get(id);
    }

    public static void setDataSourceId(String dataSourceId) {
        DynamicDataSource.dataSourceKey.set(dataSourceId);
        DynamicDataSource dynamicDataSource = (DynamicDataSource) SpringUtils.getBean("dataSource");
        dynamicDataSource.afterPropertiesSet();
    }

    public static String getDataSourceId() {
        return DynamicDataSource.dataSourceKey.get();
    }

    public static void clearKey() {
        DynamicDataSource.dataSourceKey.remove();
    }
}
