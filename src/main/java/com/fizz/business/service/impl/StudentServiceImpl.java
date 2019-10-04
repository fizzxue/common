package com.fizz.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fizz.business.mapper.StudentMapper;
import com.fizz.business.model.Student;
import com.fizz.business.service.StudentService;
import com.fizz.common.model.RespModel;
import com.fizz.utils.page.ReqPageModel;
import com.fizz.utils.page.ServiceUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;

/**
 * @author Fizz
 * @since 2019/9/30 20:49
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public RespModel page(ReqPageModel reqPageModel) {
        return ServiceUtils.page(this, reqPageModel);
    }

    @RequiresRoles(value = "role2")
    public String role() {
        System.out.println(1111111111);
        return SecurityUtils.getSubject().hasRole("aa") + "";
    }

}
