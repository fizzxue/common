package com.fizz.pattern.command;

public class WorkCommand implements Command {

    private WorkReceiver workReceiver;
    private String param;

    public WorkCommand(WorkReceiver workReceiver, String param) {
        this.workReceiver = workReceiver;
        this.param = param;
    }

    @Override
    public void execute() {
        workReceiver.work(this.param);
    }
}
