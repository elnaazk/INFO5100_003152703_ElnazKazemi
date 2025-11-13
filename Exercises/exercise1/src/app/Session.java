package app;

import java.util.*;

class Session {
    private final List<Student> students = new ArrayList<>();

    void add(Student s) { students.add(s); }

    void printAvgQuizPerStudent() {
        System.out.println("Average quiz per student:");
        for (Student s : students) {
            System.out.printf("  %s: %.2f%n", s.getName(), s.avgQuiz());
        }
    }

    void printSortedQuizScores() {
        List<Integer> all = new ArrayList<>();
        for (Student s : students) all.addAll(s.getQuizzes());
        Collections.sort(all);
        System.out.println("All quiz scores (ascending): " + all);
    }

    void printPartTimeNames() {
        System.out.println("Part-time students:");
        for (Student s : students) if (s instanceof PartTime) System.out.println("  " + s.getName());
    }

    void printFullTimeExams() {
        System.out.println("Full-time exam scores:");
        for (Student s : students) {
            if (s instanceof FullTime f) {
                System.out.printf("  %s: exam1=%d, exam2=%d%n", s.getName(), f.getExam1(), f.getExam2());
            }
        }
    }
}
