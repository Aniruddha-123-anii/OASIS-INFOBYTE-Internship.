package oasis_infobyte.task2;

import java.util.Random;
import java.util.Scanner;

/**
 * @author DEL
 *
 */
public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

        int maxAttempts = 10;
        int attempts = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a number between " + lowerBound + " and " + upperBound + ".");
        System.out.println("Your challenge is to guess the correct number within " + maxAttempts + " attempts.");

        while (attempts < maxAttempts) {
            System.out.print("Attempt " + (attempts + 1) + ": Enter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess == randomNumber) {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                break;
            } else if (userGuess < randomNumber) {
                System.out.println("Try again! Your guess is too low.");
            } else {
                System.out.println("Try again! Your guess is too high.");
            }

            int attemptsLeft = maxAttempts - attempts;
            System.out.println("Attempts left: " + attemptsLeft);
        }

        if (attempts == maxAttempts) {
            System.out.println("Sorry! You've reached the maximum number of attempts.");
            System.out.println("The correct number was: " + randomNumber);
        }

        scanner.close();
    }
}
