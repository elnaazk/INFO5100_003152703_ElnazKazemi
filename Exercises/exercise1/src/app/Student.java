package app;

import java.util.*;

abstract class Student {
    private final String name;
    private final List<Integer> quizzes;

    Student(String name, List<Integer> quizzes) {
        this.name = name;
        this.quizzes = new ArrayList<>(quizzes);
    }

    String getName() { return name; }
    List<Integer> getQuizzes() { return quizzes; }

    double avgQuiz() {
        int sum = 0;
        for (int q : quizzes) sum += q;
        return quizzes.isEmpty() ? 0 : (double) sum / quizzes.size();
    }
}
