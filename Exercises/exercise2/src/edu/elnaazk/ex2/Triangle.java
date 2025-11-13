package edu.elnaazk.ex2;

public class Triangle extends Shape {
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
