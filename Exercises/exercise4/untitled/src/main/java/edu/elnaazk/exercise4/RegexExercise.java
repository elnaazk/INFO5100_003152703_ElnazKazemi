package edu.elnaazk.exercise4;

import java.util.regex.*;

public class RegexExercise {

    public static void main(String[] args) {

        // 1. Email validation
        testPattern("Email Pattern",
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
                "elnaaz@example.com",    // positive
                "elnaaz@@example..com"); // negative

        // 2. Phone number (123-456-7890)
        testPattern("Phone Number Pattern",
                "^\\d{3}-\\d{3}-\\d{4}$",
                "123-456-7890",          // positive
                "1234567890");           // negative

        // 3. Zip code (5 digits)
        testPattern("Zip Code Pattern",
                "^\\d{5}$",
                "95123",                 // positive
                "95A23");                // negative

        // 4. Only letters (no numbers)
        testPattern("Alphabet Only Pattern",
                "^[A-Za-z]+$",
                "HelloWorld",            // positive
                "Hello123");             // negative

        // 5. Password rule (min 8 chars, at least 1 digit)
        testPattern("Password Pattern",
                "^(?=.*\\d).{8,}$",
                "mypassword1",           // positive
                "short");                // negative
    }

    public static void testPattern(String title, String pattern,
                                   String positive, String negative) {
        System.out.println("\n=== " + title + " ===");

        Pattern p = Pattern.compile(pattern);

        System.out.println("Positive test (" + positive + "): "
                + p.matcher(positive).matches());

        System.out.println("Negative test (" + negative + "): "
                + p.matcher(negative).matches());
    }
}
