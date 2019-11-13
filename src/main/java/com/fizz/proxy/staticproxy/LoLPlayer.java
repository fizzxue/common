package com.fizz.proxy.staticproxy;

/**
 * @author Fizz
 * @since 2019/11/7 15:02
 */
public class LoLPlayer implements Player {

    @Override
    public void playGame() {
        System.out.println("i always play lol");
    }
}
