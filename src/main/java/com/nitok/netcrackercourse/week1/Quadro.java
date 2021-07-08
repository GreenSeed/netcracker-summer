package com.nitok.netcrackercourse.week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quadro {

    private final double a;
    private final double b;
    private final double c;

    public Quadro(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public List<Double> solve() {
        List<Double> result = new ArrayList<>();
        Discreminant discreminant = new Discreminant();
        if (discreminant.value < 0) {
            return result;
        }
        if (discreminant.value == 0) {
            result.add(-b / (2 * a));
        } else {
            result.add((+b + discreminant.sqrt()) / (2 * a));
            result.add((-b + discreminant.sqrt()) / (2 * a));
        }
        return result;
    }

    //вложенный класс Discreminant имеет доступ к полям класса Quadro, а Quadro имеет доступ к полям Discreminant
    public class Discreminant {
        private final double value = Math.pow(b, 2) - 4 * a * c;

        public double sqrt() {
            return Math.sqrt(value);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите коэффициенты a,b,c через пробел");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        Quadro quadro = new Quadro(a, b, c);
        List<Double> res = quadro.solve();
        if (res.size() == 0) {
            System.out.println("Уравнение не имеет корней");
        } else if (res.size() == 1) {
            System.out.println("Уравнение имеет 1 корень: " + res.get(0));
        } else {
            System.out.println("Уравнение имеет 2 корня: ");
            System.out.println(res.get(0));
            System.out.println(res.get(1));
        }
    }
}
