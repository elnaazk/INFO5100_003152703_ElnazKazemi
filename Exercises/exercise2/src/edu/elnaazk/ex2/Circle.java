package edu.elnaazk.ex2;

public class Circle extends Shape {
    private double radius;
    public static final String TYPE = "Circle";

    public Circle(double radius) {
        this.radius = radius;
    }
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
    @Override
    public String toString() {
        return TYPE;
    }
}
