package com.nitok.netcrackercourse.week1.bones;

import java.util.Scanner;

public class BonesPlayer {
    protected int winCount = 0;
    protected String name;

    public String getName() {
        return name;
    }

    public BonesPlayer(String name) {
        this.name = name;
    }

    public void incWin() {
        winCount++;
    }

    public int getWin() {
        return winCount;
    }

    public int takeBones(int k) {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
