package com.nitok.netcrackercourse.week2.bones;

import java.util.Scanner;

public class BonesPlayer extends AbstractBonesPlayer {
    protected String name;


    public BonesPlayer(String name) {
        this.name = name;
    }

    public int takeBones(int k) {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public String getName() {
        return name;
    }
}
