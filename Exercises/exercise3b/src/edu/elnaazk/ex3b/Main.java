package edu.elnaazk.ex3b;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Create shapes (same as exercise 2)
        Shape.defaultColor = "red";
        Shape[] shapes = {
                new Rectangle(4, 5),
                new Square(6),
                new Circle(3),
                new Triangle(3, 4, 5)
        };

        // Serialize shapes to a file
        String filename = "shapes.ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(shapes);
            System.out.println("✅ Shapes serialized to " + filename);
        } catch (IOException e) {
            System.out.println("Error during serialization: " + e.getMessage());
        }

        Shape.defaultColor = "green";

        // Deserialize shapes from the file
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            Shape[] loadedShapes = (Shape[]) in.readObject();
            System.out.println("\n✅ Deserialized shapes:");
            for (Shape s : loadedShapes) {
                System.out.println(
                        s + " area=" + s.calculateArea() +
                                " perimeter=" + s.calculatePerimeter() +
                                " desc=" + s.getDescription()
                );
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error during deserialization: " + e.getMessage());
        }
    }
}
