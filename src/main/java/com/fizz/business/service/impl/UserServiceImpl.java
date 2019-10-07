package com.fizz.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fizz.business.mapper.UserMapper;
import com.fizz.business.model.User;
import com.fizz.business.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Fizz
 * @since 2019/9/30 20:49
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
