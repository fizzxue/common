package com.fizz.proxy.staticproxy;

/**
 * @author Fizz
 * @since 2019/11/7 15:04
 */
public class Test {
    public static void main(String[] args) {
        Player player = new OutLoLPlayer(new LoLPlayer());
        player.playGame();
    }
}
