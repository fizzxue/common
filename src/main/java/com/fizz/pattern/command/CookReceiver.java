package com.fizz.pattern.command;

public class CookReceiver {

    public String cook(String foodName) {
        String output = foodName + " is ok";
        System.out.println(output);
        return output;
    }

    public String cookBefore(String foodName) {
        String output = "before cook" + foodName;
        System.out.println(output);
        return output;
    }

    public String cookAfter(String foodName) {
        String output = "after cook" + foodName;
        System.out.println(output);
        return output;
    }
}
