package com.fizz.business.acl.service;

import com.fizz.business.acl.entity.User;
import com.fizz.business.acl.mapper.UserMapper;
import com.fizz.business.acl.service.impl.UserServiceImpl;
import mockit.Injectable;
import mockit.Mocked;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class IUserServiceTest {
    @Mocked
    private UserMapper userMapper;

    @Injectable
    private UserServiceImpl userService;

    @Test
    public void t1(){
        when(userMapper.selectList(any())).thenReturn(Arrays.asList(new User()));
        List<User> users = userService.list();
        assertEquals(2, users.size(), "error");
    }
}