package com.nitok.netcrackercourse.week3;

public class Person {
    private String lastName;
    private String firstName;
    private String surName;

    public Person(String lastName, String firstName, String surName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.surName = surName;
    }

    public Person(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Person(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        String res = lastName;
        if (firstName != null) {
            res += " " + firstName.substring(0, 1).toLowerCase() + ".";
        }
        if (surName != null) {
            res += " " + surName.substring(0, 1).toLowerCase() + ".";
        }
        return res;
    }
}
