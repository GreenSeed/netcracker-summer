package com.nitok.netcrackercourse.week1.address;

public class Address {
    private final Country country;
    private final String city;
    private final String street;
    private final int housNum;
    private final boolean hasApartment;
    private final int apartmentNum;

    public Address(Country country, String city, String street, int housNum, int apartmentNum) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.housNum = housNum;
        this.hasApartment = true;
        this.apartmentNum = apartmentNum;
    }

    public Address(Country country, String city, String street, int housNum) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.housNum = housNum;
        this.hasApartment = false;
        this.apartmentNum = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Address))
            return super.equals(obj);
        Address addr = (Address) obj;
        return country.equals(addr.country) &&
                city.equals(addr.city) &&
                street.equals(addr.street) &&
                housNum == addr.housNum &&
                (!hasApartment || apartmentNum == addr.apartmentNum);
    }

    public int getApartmentNum() {
        return apartmentNum;
    }

    public int getHousNum() {
        return housNum;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public boolean isHasApartment() {
        return hasApartment;
    }

    @Override
    public String toString() {
        return country.name() + ", " + city + ", " + street + ", " + housNum + (hasApartment ? "," + apartmentNum : "");
    }
}
