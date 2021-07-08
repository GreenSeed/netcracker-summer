package com.nitok.netcrackercourse.week2.task4;

public class TheNumbersImpl implements TwoNumbers {
    private int x = 0;
    private int y = 0;

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getDifference() {
        return Math.abs(x - y);
    }
}
