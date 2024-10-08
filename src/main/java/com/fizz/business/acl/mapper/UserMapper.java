package com.fizz.business.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fizz.business.acl.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Fizz
 * @since 2019-10-09
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<String> multiSelect(List<String> ids);

    User queryById(String id);

    @Insert("insert into user (id,account,password) values (#{id}, #{account}, #{password})")
    int insert(User user);
}
