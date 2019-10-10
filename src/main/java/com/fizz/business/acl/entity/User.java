package com.fizz.business.acl.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Fizz
 * @since 2019-10-09
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码，md5加密存储
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别，1男2女
     */
    private Integer sex;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
