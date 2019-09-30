package com.fizz.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fizz.business.mapper.StudentMapper;
import com.fizz.business.model.Student;
import com.fizz.business.service.StudentService;
import com.fizz.utils.spring.SpringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Fizz
 * @since 2019/9/30 20:49
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean saveForTran1() throws Exception {
        boolean xm = this.save(new Student("7", "xm", Short.parseShort("1")));
        if (3 / 2 > 0) {
            throw new RuntimeException("插入运行时异常");
        }
        return xm;
    }

    @Override
    @Transactional()
    public boolean saveForTran2() throws Exception {
        try {
            boolean xm = SpringUtils.getBean(StudentService.class).saveForTran1();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        boolean xh = SpringUtils.getBean(StudentService.class).save(new Student("8", "xh", Short.parseShort("2")));
//        Thread.sleep(3000);
        /*if (3 / 2 > 0) {
            throw new Exception("插入运行时异常");
        }*/
        return xh;
    }
}
