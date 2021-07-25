package ru.skillbench.tasks.basics.math;

import java.util.Arrays;
import java.util.Comparator;

public class ComplexNumberImpl implements ComplexNumber {
    double x = 0, y = 0;

    @Override
    public double getRe() {
        return x;
    }

    @Override
    public double getIm() {
        return y;
    }

    @Override
    public boolean isReal() {
        return y == 0;
    }

    @Override
    public void set(double re, double im) {
        this.x = re;
        this.y = im;
    }

    @Override
    public void set(String value) throws NumberFormatException {
        String newValue = value;
        if (newValue.startsWith("-") || newValue.startsWith("+")) {
            newValue = newValue.substring(1);
        }
        String[] split = newValue.split("[+\\-]");

        if (split.length == 2) {
            int reSign = value.startsWith("-") ? -1 : 1;
            int imSign = newValue.contains("-") ? -1 : 1;
            this.x = reSign * Double.parseDouble(split[0]);
            this.y = imSign * Double.parseDouble(split[1].replace("i", ""));
        } else {
            if (value.contains("i")) {
                this.x = 0;
                this.y = Double.parseDouble(value.replace("i", ""));
            } else {
                this.x = Double.parseDouble(value);
                this.y = 0;
            }
        }
    }

    @Override
    public ComplexNumber copy() {
        ComplexNumber complexNumber = new ComplexNumberImpl();
        complexNumber.set(this.x, this.y);
        return complexNumber;
    }

    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        return (ComplexNumber) super.clone();
    }

    @Override
    public int compareTo(ComplexNumber other) {
        Double abs1 = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
        Double abs2 = Math.sqrt(Math.pow(other.getRe(), 2) + Math.pow(other.getIm(), 2));
        return abs1.compareTo(abs2);
    }

    @Override
    public void sort(ComplexNumber[] array) {
        Arrays.sort(array, ComplexNumber::compareTo);
    }

    @Override
    public ComplexNumber negate() {
        x = -x;
        y = -y;
        return this;
    }

    @Override
    public ComplexNumber add(ComplexNumber arg2) {
        this.x += arg2.getRe();
        this.y += arg2.getIm();
        return this;
    }

    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        double newX = this.x * arg2.getRe() - this.y * arg2.getIm();
        double newY = this.y * arg2.getRe() + this.x * arg2.getIm();
        this.x = newX;
        this.y = newY;
        return this;
    }

    @Override
    public String toString() {
        if (this.x == 0) {
            return this.y + "i";
        }
        if (this.y == 0) {
            return String.valueOf(this.x);
        }
        return this.x + (this.y > 0 ? "+" : "") + this.y + "i";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if(!(obj instanceof ComplexNumber)){
            return super.equals(obj);
        }
        ComplexNumber other = (ComplexNumber) obj;
        return this.x == other.getRe() && this.y == other.getIm();
    }
}
