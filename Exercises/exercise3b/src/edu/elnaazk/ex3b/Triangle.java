package edu.elnaazk.ex3b;

public class Triangle extends Shape {
    private static final long serialVersionUID = 1L;

    private double a, b, c;
    public static final String TYPE = "Triangle";

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calculateArea() {
        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double calculatePerimeter() {
        return a + b + c;
    }

    @Override
    public String toString() {
        return TYPE;
    }
}
