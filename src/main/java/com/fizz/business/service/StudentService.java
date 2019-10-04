package com.fizz.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fizz.business.model.Student;
import com.fizz.common.model.RespModel;
import com.fizz.utils.page.ReqPageModel;

/**
 * @author Fizz
 * @since 2019/9/30 20:50
 */
public interface StudentService extends IService<Student> {

    RespModel page(ReqPageModel reqPageModel);

    String role();
}
