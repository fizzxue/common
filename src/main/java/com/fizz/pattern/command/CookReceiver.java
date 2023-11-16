package com.fizz.pattern.command;

public class CookReceiver {

    public String cook(String foodName) {
        String output = foodName + " is ok";
        System.out.println(output);
        return output;
    }
}
