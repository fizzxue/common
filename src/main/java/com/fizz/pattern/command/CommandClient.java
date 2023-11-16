package com.fizz.pattern.command;

public class CommandClient {

    public static void main(String[] args) {
//        before();
        after();
    }

    private static void after() {
        Command c1 = new CookCommand(new CookReceiver(), "烤鱼");
        Invoker invoker = new Invoker(c1);
        invoker.execCommand();

        Command c2 = new WorkCommand(new WorkReceiver(), "游泳");
        invoker.setCommand(c2);
        invoker.execCommand();
    }

    private static void before() {
        WorkReceiver workReceiver = new WorkReceiver();
        workReceiver.work("滑行");

        CookReceiver cookReceiver = new CookReceiver();
        cookReceiver.cook("烤肉");
    }
}
