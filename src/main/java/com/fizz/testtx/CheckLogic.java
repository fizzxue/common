package com.fizz.testtx;

import com.fizz.business.acl.entity.User;
import com.fizz.business.acl.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CheckLogic {

    @Resource
    private UserMapper userMapper;

    public void checkAll(){
        check1();
        try {
            System.out.println(1/0);
            check2();
            check3();
//            return null;
        } catch (Exception e) {
            User updateUser = new User();
            updateUser.setId("1");
            updateUser.setName("checkAll Exception");
            userMapper.updateById(updateUser);
//            return e;
            throw e;
        }
    }

    public void check1(){
        System.out.println("================== check1");
    }

    public void check2(){
        System.out.println("================== check2");
    }

    public void check3(){
        System.out.println("================== check3");
    }
}
