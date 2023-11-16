package com.fizz.pattern.command;

import lombok.Data;

@Data
public class Invoker {

    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }



    public void execCommand() {
        command.execute();
    }
}
