package com.fizz.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fizz.business.model.Student;

/**
 * @author Fizz
 * @since 2019/9/30 20:50
 */
public interface StudentService extends IService<Student> {

    boolean saveForTran1() throws Exception;

    boolean saveForTran2() throws Exception;
}
