package com.fizz.testtx;

import com.fizz.business.acl.entity.User;
import com.fizz.business.acl.mapper.UserMapper;
import com.fizz.utils.spring.SpringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class ThisTest {

    @Resource
    private UserMapper userMapper;


//    @Transactional
    public void t1(){
        User updateUser = new User();
        updateUser.setId("3");
        updateUser.setName("t1");
        userMapper.updateById(updateUser);
        try {
            SpringUtils.getBean(ThisTest.class).t2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void t2(){
        User updateUser = new User();
        updateUser.setId("4");
        updateUser.setName("t2");
        userMapper.updateById(updateUser);
//        System.out.println(1/0);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void t3(){
        User updateUser = new User();
        updateUser.setId("5");
        updateUser.setName("t3");
        userMapper.updateById(updateUser);
        System.out.println(1/0);
    }

    public void t4(){
        ThisTest bean = SpringUtils.getBean(ThisTest.class);
        bean.t2();
        bean.t3();
//        System.out.println(1/0);
    }
}
