package com.nitok.netcrackercourse.week2.bones;

import java.util.Random;

public class ComputerBonesPlayer extends AbstractBonesPlayer {
    public ComputerBonesPlayer() {

    }

    @Override
    public int takeBones(int k) {
        return new Random().nextInt(k * 6);
    }

    @Override
    public String getName() {
        return "Компьютер";
    }
}
