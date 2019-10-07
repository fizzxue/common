package com.fizz.business.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Fizz
 * @since 2019/10/4 21:13
 */
@TableName("user")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 6376425580994398081L;

    private String id;

    private String account;

    private String password;
}
