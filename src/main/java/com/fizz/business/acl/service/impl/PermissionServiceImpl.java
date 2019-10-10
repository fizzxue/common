package com.fizz.business.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fizz.business.acl.entity.Permission;
import com.fizz.business.acl.mapper.PermissionMapper;
import com.fizz.business.acl.service.IPermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Fizz
 * @since 2019-10-09
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
