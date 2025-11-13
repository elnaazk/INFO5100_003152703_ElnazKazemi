package app;

import java.util.*;

public class SessionApp {
    private static List<Integer> fifteen(int seed) {
        Random r = new Random(seed);
        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < 15; i++) q.add(50 + r.nextInt(51));
        return q;
    }

    public static void main(String[] args) {
        Session s = new Session();
        for (int i = 1; i <= 10; i++) s.add(new PartTime("PT" + i, fifteen(i)));
        for (int i = 1; i <= 10; i++) s.add(new FullTime("FT" + i, fifteen(100 + i), 60 + i, 70 + i));

        s.printAvgQuizPerStudent();
        s.printSortedQuizScores();
        s.printPartTimeNames();
        s.printFullTimeExams();
    }
}
