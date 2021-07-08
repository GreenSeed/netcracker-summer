package com.nitok.netcrackercourse.week2.bones;

public abstract class AbstractBonesPlayer implements Player {
    protected int winCount = 0;

    public void incWin() {
        winCount++;
    }

    public int getWin() {
        return winCount;
    }
}
