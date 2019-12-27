package com.fizz.es;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Fizz
 * @since 2019/12/13 16:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ESTest {

    @Resource
    private ElasticsearchTemplate es;

    @Test
    public void t1() {
        boolean index = es.putMapping(Student.class);
    }
}
