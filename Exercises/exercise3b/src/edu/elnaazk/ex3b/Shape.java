package edu.elnaazk.ex3b;

import java.io.Serializable;

public abstract class Shape implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String defaultColor = "blue";

    public abstract double calculateArea();
    public abstract double calculatePerimeter();

    public String getDescription() {
        return "This shape is color " + defaultColor;
    }
}
