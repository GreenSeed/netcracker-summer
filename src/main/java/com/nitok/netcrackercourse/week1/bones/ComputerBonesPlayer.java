package com.nitok.netcrackercourse.week1.bones;

import java.util.Random;

public class ComputerBonesPlayer extends BonesPlayer {
    public ComputerBonesPlayer() {
        super("компьютер");
    }

    @Override
    public int takeBones(int k) {
        return new Random().nextInt(k * 6);
    }
}
