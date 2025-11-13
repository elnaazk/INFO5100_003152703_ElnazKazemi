package app;

import java.util.*;

class Student {
    private final String name;
    private final List<Integer> quizzes;

    Student(String name, List<Integer> quizzes) {
        this.name = name;
        this.quizzes = quizzes;
    }

    String getName() { return name; }
    List<Integer> getQuizzes() { return quizzes; }

    double averageQuiz() {
        int sum = 0;
        for (int q : quizzes) sum += q;
        return quizzes.isEmpty() ? 0.0 : (sum * 1.0) / quizzes.size();
    }
}

class PartTime extends Student {
    PartTime(String name, List<Integer> quizzes) { super(name, quizzes); }
}

class FullTime extends Student {
    private final int exam1;
    private final int exam2;

    FullTime(String name, List<Integer> quizzes, int exam1, int exam2) {
        super(name, quizzes);
        this.exam1 = exam1;
        this.exam2 = exam2;
    }

    int getExam1() { return exam1; }
    int getExam2() { return exam2; }
}

class Session {
    private final List<Student> students = new ArrayList<>();

    void addStudent(Student s) { students.add(s); }

    public void printAverageQuizPerStudent() {
        System.out.println("Average quiz per student:");
        for (Student s : students) {
            System.out.printf("  %s -> %.2f%n", s.getName(), s.averageQuiz());
        }
    }

    public void printSortedQuizScores() {
        List<Integer> all = new ArrayList<>();
        for (Student s : students) all.addAll(s.getQuizzes());
        Collections.sort(all);
        System.out.println("All quiz scores (ascending):");
        System.out.println(all);
    }

    public void printPartTimeNames() {
        System.out.println("Part-time students:");
        for (Student s : students) if (s instanceof PartTime) System.out.println("  " + s.getName());
    }

    public void printFullTimeExamScores() {
        System.out.println("Full-time exam scores:");
        for (Student s : students)
            if (s instanceof FullTime f)
                System.out.println("  " + f.getName() + " -> " + f.getExam1() + ", " + f.getExam2());
    }
}

public class SessionApp {
    static List<Integer> fifteen(Random r) {
        List<Integer> list = new ArrayList<>(15);
        for (int i = 0; i < 15; i++) list.add(50 + r.nextInt(51));
        return list;
    }

    public static void main(String[] args) {
        Session session = new Session();
        Random r = new Random(42);

        for (int i = 1; i <= 20; i++) {
            String name = "Student" + i;
            if (i % 2 == 0) {
                session.addStudent(new FullTime(name, fifteen(r), 60 + r.nextInt(41), 60 + r.nextInt(41)));
            } else {
                session.addStudent(new PartTime(name, fifteen(r)));
            }
        }

        System.out.println("=== SESSION REPORT ===");
        session.printAverageQuizPerStudent();
        System.out.println();
        session.printSortedQuizScores();
        System.out.println();
        session.printPartTimeNames();
        System.out.println();
        session.printFullTimeExamScores();
    }
}
