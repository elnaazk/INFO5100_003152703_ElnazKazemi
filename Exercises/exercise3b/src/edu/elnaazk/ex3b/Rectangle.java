package edu.elnaazk.ex3b;

public class Rectangle extends Shape {
    private static final long serialVersionUID = 1L;

    private double width;
    private double height;
    public static final String TYPE = "Rectangle";

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return TYPE;
    }
}
