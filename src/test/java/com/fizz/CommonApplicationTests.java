package com.fizz;

import com.fizz.cache.service.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonApplicationTests {

    @Resource
    private CacheService<String, Integer> cacheService;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void contextLoads() {
        int i =0;
        i += 1;
        System.out.println(cacheService);

        cacheService.put("xll", 22);
        System.out.println(cacheService.get("xll"));
        cacheService.put("xn", 26);
        System.out.println(cacheService.getAll(Arrays.asList("xll", "xn")));
    }

    @Test
    public void jdbcQuery() {
        System.out.println(jdbcTemplate.queryForList("select * from student"));
    }

}
