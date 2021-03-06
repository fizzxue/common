package com.fizz.utils.date;

import com.fizz.common.enums.RespEnum;
import com.fizz.common.model.RespModel;

/**
 * @author Fizz
 * @since 2019/9/27 16:20
 */
public final class RespUtils {

    private RespUtils() {
    }

    public static <R> RespModel<R> success(R data) {
        return new RespModel<R>().setCode(RespEnum.SUCCESS).setData(data);
    }

    public static <R> RespModel<R> failure(R data) {
        return new RespModel<R>().setCode(RespEnum.FAILURE).setData(data);
    }
}
