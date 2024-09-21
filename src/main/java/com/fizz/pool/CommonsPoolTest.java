package com.fizz.pool;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

import java.util.ArrayList;
import java.util.List;

/**
 * commons-pool使用测试
 */
public class CommonsPoolTest {

    public static void main(String[] args) throws Exception {
        GenericObjectPool.Config config = new GenericObjectPool.Config();
        config.maxActive = 10;
        config.maxIdle = 10;
        config.timeBetweenEvictionRunsMillis = 1000*5;
        config.minEvictableIdleTimeMillis = 1;

        ObjectPool pool = new GenericObjectPool(new MyPoolableObjectFactory(), config);

        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Object o = pool.borrowObject();
            System.out.println(o);
            list.add(o);
        }
        for (Object o : list) {
            pool.returnObject(o);
        }
        System.in.read();
    }
}
