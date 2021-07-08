package com.nitok.netcrackercourse.week1.address;

import java.time.LocalDate;

public class Human {
    private final String firstName;
    private final String lastName;
    private final Address address;
    private final LocalDate birthdate;

    public Human(String firstName, String lastName, Address address, LocalDate birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthdate = birthdate;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
