package edu.elnaazk.ex3b;

public class Square extends Rectangle {
    private static final long serialVersionUID = 1L;

    public static final String TYPE = "Square";

    public Square(double side) {
        super(side, side);
    }

    @Override
    public String toString() {
        return TYPE;
    }
}
