package com.nitok.netcrackercourse.week3;

import java.util.InputMismatchException;

public class Phone {
    private String countryCode;
    private String number;

    public Phone(String phone) {
        if (!phone.startsWith("8") && !phone.startsWith("+") || phone.length() < 10) {
            throw new InputMismatchException();
        }
        String substring = phone.substring(phone.length() - 10);
        if (phone.startsWith("+")) {
            number = substring;
            countryCode = phone.substring(1, phone.length() - 10);
        } else if (phone.startsWith("8")) {
            number = substring;
            countryCode = "7";
        }
    }

    @Override
    public String toString() {
        return "+" + countryCode + number.substring(0, 3) + "-" + number.substring(3, 6) + "-" + number.substring(4);
    }
}
