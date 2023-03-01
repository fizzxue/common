package com.fizz.es;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import javax.annotation.Resource;

/**
 * @author Fizz
 * @since 2019/12/13 16:10
 */
@SpringBootTest
public class ESTest {

    @Resource
    private ElasticsearchTemplate es;

    @Test
    public void t1() {
    }
}
