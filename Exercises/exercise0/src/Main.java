import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        int choice;

        // The menu repeat until the user chooses Exit.
        do {
            System.out.println("***Number Guessing Game***");
            System.out.println("1. Start Game");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            // Read the menu number the user types
            choice = in.nextInt();

            switch (choice) {
                case 1:
                    // + 1  makes it 1 to 10.
                    int number = rand.nextInt(10) + 1;
                    boolean guessed = false;
                    System.out.println("I picked a number between 1 and 10. You have 5 attempts.");

                    // 5 attempts
                    for (int i = 1; i <= 5; i++) {
                        System.out.print("Attempt " + i + ": ");
                        int guess = in.nextInt();

                        if (guess == number) {
                            System.out.println("Correct!");
                            guessed = true;
                            break;
                        } else if (guess > number) {
                            System.out.println("Too High!");
                        } else {
                            System.out.println("Too Low!");
                        }
                    }
                    // if not guessed in 5 tries, reveal the number
                    if (!guessed) {
                        System.out.println("Sorry! The correct number was: " + number);
                    }
                    // after a round, menu will show again
                    break;

                case 2:
                    System.out.println("Exiting game. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
            // repeat menu until Exit chosen
            System.out.println();
        }while (choice != 2);
    }
}