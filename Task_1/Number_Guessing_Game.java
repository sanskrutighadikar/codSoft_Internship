import java.util.Random;
import java.util.Scanner;

public class Number_Guessing_Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();


        int Start = 1;
        int End = 100;
        int maxAttempts = 5;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        boolean playAgain = true;
        while (playAgain) {
            int targetNumber = random.nextInt(End - Start + 1) + Start;
            System.out.println("The Random number is: "+targetNumber);
            int attempts = 0;
            boolean guessed = false;

            System.out.println("\nI have generated a number between " + Start + " and " + End + ". Can you guess it?");

            while (!guessed && attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == targetNumber) {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    guessed = true;
                    score += maxAttempts - attempts + 1;
                } else if (guess < targetNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!guessed) {
                System.out.println("Oops! You couldn't guess the number. The correct number was: " + targetNumber);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainChoice = scanner.next();
            playAgain = playAgainChoice.equalsIgnoreCase("yes");
        }

        System.out.println("\nGame over! Your total score is: " + score);
        scanner.close();
    }
}