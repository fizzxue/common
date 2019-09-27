package com.fizz.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fizz.common.enums.RespEnum;
import lombok.Data;
import lombok.experimental.Accessors;

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
}
