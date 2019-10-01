package com.fizz.business.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Fizz
 * @since 2019/9/30 20:46
 */
@TableName("student")
@Data
@NoArgsConstructor
public class Student implements Serializable {
    private static final long serialVersionUID = -320511760353928163L;

    private String id;

    private String name;

    private Short sex;

    private Date createTime;

    private Date updateTime;

    public Student(String id, String name, short sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
}
