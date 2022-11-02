package com.fizz.testtx;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fizz.business.acl.entity.User;
import com.fizz.business.acl.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserLogic {

    @Resource
    private UserMapper userMapper;

    @Resource
    private CheckLogic checkLogic;

    public void updateUser() {
        User updateUser1 = new User();
        updateUser1.setId("2");
        updateUser1.setName("updateUserBefore");
        userMapper.update(updateUser1, new UpdateWrapper<>(updateUser1));
        checkLogic.checkAll();
        User updateUser2 = new User();
        updateUser2.setId("2");
        updateUser2.setName("updateUserAfter");
        userMapper.update(updateUser2, new UpdateWrapper<>(updateUser2));
//        if (throwable != null) {
//            throw throwable;
//        }
    }
}
