package com.fizz.config.shiro;

import com.fizz.business.acl.entity.User;
import com.fizz.business.acl.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Fizz
 * @since 2019/10/3 10:40
 */
public class AuthRealm extends AuthorizingRealm {

    @Resource
    private IUserService iUserService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        User userByAccount = iUserService.getUserByAccount(userName);
        if (Objects.isNull(userByAccount)) {
            throw new UnknownAccountException("用户名或密码错误！");
        }
        return new SimpleAuthenticationInfo(userByAccount, userByAccount.getPassword(),
                ByteSource.Util.bytes(userName + ShiroConfig.SALT), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Set<String> roles = new HashSet<>();
        roles.add("role1");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roles);
        return simpleAuthorizationInfo;
    }

}
