package com.fizz.proxy.staticproxy;

/**
 * @author Fizz
 * @since 2019/11/7 15:03
 */
public class OutLoLPlayer implements Player {
    LoLPlayer loLPlayer;

    public OutLoLPlayer(LoLPlayer loLPlayer) {
        this.loLPlayer = loLPlayer;
    }

    @Override
    public void playGame() {
        System.out.println("开个夜机");
        loLPlayer.playGame();
    }
}
