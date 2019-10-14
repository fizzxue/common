package com.fizz.config.datasource;

import com.fizz.utils.spring.SpringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>使用步骤：</p>
 * <blockquote><pre>
 *     DynamicDataSource.dataSourcesMap.put(dataSourceKey, druidDataSource);
 *     DynamicDataSource.setDataSource(dataSourceKey);
 *     调用业务代码</i>
 *     DynamicDataSource.clear();
 * </pre></blockquote>
 *
 * @author Fizz
 * @since 2019/10/14 10:49
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> dataSourceKey = ThreadLocal.withInitial(() -> "defaultDataSource");

    public static Map<Object, Object> dataSourcesMap = new ConcurrentHashMap<>(10);

    static {
        dataSourcesMap.put("defaultDataSource", SpringUtils.getBean("defaultDataSource"));
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSource.dataSourceKey.get();
    }

    public static void setDataSource(String dataSource) {
        DynamicDataSource.dataSourceKey.set(dataSource);
        DynamicDataSource dynamicDataSource = (DynamicDataSource) SpringUtils.getBean("dataSource");
        dynamicDataSource.afterPropertiesSet();
    }

    public static String getDataSource() {
        return DynamicDataSource.dataSourceKey.get();
    }

    public static void clear() {
        DynamicDataSource.dataSourceKey.remove();
    }
}
