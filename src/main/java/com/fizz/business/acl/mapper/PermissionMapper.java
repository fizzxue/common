package com.fizz.business.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fizz.business.acl.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Fizz
 * @since 2019-10-09
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
