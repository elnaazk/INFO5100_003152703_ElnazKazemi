package edu.elnaazk.ex2;

public class Main {
    public static void main(String[] args) {
        Shape.defaultColor = "red";
        Shape[] shapes = {
                new Rectangle(4, 5),
                new Square(6),
                new Circle(3),
                new Triangle(3, 4, 5)
        };
        for (Shape s : shapes) {
            System.out.println(
                    s + " area=" + s.calculateArea() +
                            " perimeter=" + s.calculatePerimeter() +
                            " desc=" + s.getDescription()
            );
        }
    }
}
