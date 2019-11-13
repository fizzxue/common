package com.fizz;

import com.fizz.business.acl.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonApplicationTests {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
    }

}
