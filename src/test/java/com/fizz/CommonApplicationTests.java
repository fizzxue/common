package com.fizz;

import com.alibaba.druid.pool.DruidDataSource;
import com.fizz.business.acl.entity.User;
import com.fizz.business.acl.mapper.UserMapper;
import com.fizz.config.DynamicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonApplicationTests {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        DruidDataSource druidDataSource1 = (DruidDataSource) DynamicDataSource.getDataSource();
        System.out.println(druidDataSource1.getMaxActive());
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/sys?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&useAffectedRows=true");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        DynamicDataSource.setDataSource(druidDataSource);
        DruidDataSource druidDataSource2 = (DruidDataSource) DynamicDataSource.getDataSource();
        System.out.println(druidDataSource2.getMaxActive());
        List<Map<String, Object>> maps1 = jdbcTemplate.queryForList("select * from sys_config");
        System.out.println(maps1);
        DynamicDataSource.clear();
        System.out.println(DynamicDataSource.getDataSource());
    }

}
