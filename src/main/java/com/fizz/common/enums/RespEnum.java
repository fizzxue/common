package com.fizz.common.enums;

import lombok.Getter;

/**
 * @author fizz
 * @since 2019/7/23 16:15
 */
@Getter
public enum RespEnum {
    SUCCESS(20000, "操作成功"),
    FAILURE(500, "操作失败");

    private Integer code;

    private String message;

    RespEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
