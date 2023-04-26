package com.fizz.statemachine.enums;

public enum EventEnum {

    PAY(1, "支付"),
    RECEIVE(2, "收货"),
    REFUND(3, "退货");

    EventEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;
}
