package com.fizz.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fizz.common.enums.RespEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @param <T>
 * @author Fizz
 * @since 2019/9/11 10:44
 */
@Data
@Accessors(chain = true)
public class RespModel<T> {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private T data;

    public RespModel<T> setCode(RespEnum respEnum) {
        this.code = respEnum.getCode();
        this.message = respEnum.getMessage();
        return this;
    }

    public static <R> RespModel<R> success(){
        return new RespModel<R>().setCode(RespEnum.SUCCESS);
    }
}
