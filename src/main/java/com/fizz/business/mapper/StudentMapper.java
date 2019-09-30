package com.fizz.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fizz.business.model.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Fizz
 * @since 2019/9/30 20:49
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
