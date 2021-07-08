package com.nitok.netcrackercourse.week2.task4;

public class Starter {
    public static void main(String[] args) {
        TheNumbersImpl impl = new TheNumbersImpl();
        impl.setX(10);
        impl.setY(4);
        System.out.println("impl.getDifference() = " + impl.getDifference());
    }
}
