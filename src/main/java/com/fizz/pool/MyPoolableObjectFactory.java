package com.fizz.pool;

import org.apache.commons.pool.BasePoolableObjectFactory;

/**
 * @author Fizz
 * @since 2024/9/21 13:36
 */
public class MyPoolableObjectFactory extends BasePoolableObjectFactory {

    @Override
    public Object makeObject() throws Exception {
        return new Object();
    }
}
