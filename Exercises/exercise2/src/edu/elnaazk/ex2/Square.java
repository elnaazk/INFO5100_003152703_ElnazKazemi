package edu.elnaazk.ex2;

public class Square extends Rectangle {
    public static final String TYPE = "Square";
    public Square(double side) {
        super(side, side);
    }
    @Override
    public String toString() {
        return TYPE;
    }
}
