package app;

import java.util.List;

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
