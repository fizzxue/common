package com.fizz.pattern.command;

public class CookCommand implements Command {

    private CookReceiver cookReceiver;

    private String param;

    public CookCommand(CookReceiver cookReceiver, String param) {
        this.cookReceiver = cookReceiver;
        this.param = param;
    }

    @Override
    public void execute() {
        cookReceiver.cookBefore(param);
        cookReceiver.cook(param);
        cookReceiver.cookAfter(param);
    }
}
