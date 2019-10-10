package com.fizz.business.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fizz.business.acl.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Fizz
 * @since 2019-10-09
 */
public interface IUserService extends IService<User> {

    User getUserByAccount(String account);
}
