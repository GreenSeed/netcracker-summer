package ru.skillbench.tasks.basics.math;

import java.util.Arrays;

public class ArrayVectorImpl implements ArrayVector {
    double[] elements = new double[0];

    @Override
    public void set(double... elements) {
        this.elements = elements;
    }

    @Override
    public double[] get() {
        return elements;
    }

    @Override
    public ArrayVector clone() {
        ArrayVectorImpl arrayVector = new ArrayVectorImpl();
        arrayVector.set(this.elements.clone());
        return arrayVector;
    }

    @Override
    public int getSize() {
        return this.elements.length;
    }

    @Override
    public void set(int index, double value) {
        if (index < 0) {
            return;
        }
        if(index>this.elements.length){
            this.elements = Arrays.copyOf(this.elements, index + 1);
        }
        this.elements[index] = value;
    }

    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {
        return this.elements[index];
    }

    @Override
    public double getMax() {
        double max = Double.MIN_VALUE;
        for (double element : elements) {
            max = Math.max(max, element);
        }
        return max;
    }

    @Override
    public double getMin() {
        double min = Double.MAX_VALUE;
        for (double element : elements) {
            min = Math.min(min, element);
        }
        return min;
    }

    @Override
    public void sortAscending() {
        Arrays.sort(elements);
    }

    @Override
    public void mult(double factor) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= factor;
        }
    }

    @Override
    public ArrayVector sum(ArrayVector anotherVector) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] += anotherVector.get(i);
        }
        return null;
    }

    @Override
    public double scalarMult(ArrayVector anotherVector) {
        double res = 0;
        for (int i = 0; i < elements.length; i++) {
            res += elements[i] * anotherVector.get(i);
        }
        return res;
    }

    @Override
    public double getNorm() {
        double res = 0;
        for (double element : elements) {
            res += Math.pow(element, 2);
        }
        return Math.sqrt(res);
    }
}
