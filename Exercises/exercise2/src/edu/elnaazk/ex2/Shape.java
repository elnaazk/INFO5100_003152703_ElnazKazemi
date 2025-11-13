package edu.elnaazk.ex2;

public abstract class Shape {
    public static String defaultColor = "blue";
    public abstract double calculateArea();
    public abstract double calculatePerimeter();
    public String getDescription() {
        return "This shape is color " + defaultColor;
    }
}
