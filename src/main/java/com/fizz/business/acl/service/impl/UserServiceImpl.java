package com.fizz.business.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fizz.business.acl.entity.User;
import com.fizz.business.acl.mapper.UserMapper;
import com.fizz.business.acl.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Fizz
 * @since 2019-10-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User getUserByAccount(String account) {
        System.out.println("====================进入UserServiceImpl.getUserByAccount");
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("account", account);
        return this.getOne(qw);
    }
}
