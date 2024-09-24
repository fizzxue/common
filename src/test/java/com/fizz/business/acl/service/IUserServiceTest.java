package com.fizz.business.acl.service;

import com.alibaba.fastjson.JSON;
import com.fizz.business.acl.entity.User;
import com.fizz.business.acl.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class IUserServiceTest {

    @Resource
    private IUserService userService;

    @Resource
    private UserMapper userMapper;

    @Test
    public void t1(){
        List<String> strings = userMapper.multiSelect(Arrays.asList("1", "2", "3"));
        System.out.println(JSON.toJSON(strings));
    }

    @Test
    public void t2(){
        User batchSave = new User();
        batchSave.setId(System.currentTimeMillis() + "");
        batchSave.setName("batchSave");
        batchSave.setAccount("batchSave");
        batchSave.setPassword("batchSave");
        userService.saveBatch(Arrays.asList(batchSave));
    }
}