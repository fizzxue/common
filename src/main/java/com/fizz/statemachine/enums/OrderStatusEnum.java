package com.fizz.statemachine.enums;

public enum OrderStatusEnum {

    UN_PAY(1, "未支付"),
    PAID(2, "已支付"),
    FINISHED(3, "订单完成"),
    CANCEL(4, "订单取消");

    OrderStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;
}
