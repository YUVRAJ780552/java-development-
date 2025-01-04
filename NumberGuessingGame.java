import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static int playRound() {
        Random rand = new Random();
        try (Scanner scanner = new Scanner(System.in)) {
            int targetNumber = rand.nextInt(100) + 1;
            int attemptsLeft = 10;  
            int guess;
            
            System.out.println("I'm thinking of a number between 1 and 100.");
            System.out.println("You have 10 attempts to guess it.");
            
            while (attemptsLeft > 0) {
                
                System.out.print("Enter your guess: ");
                guess = scanner.nextInt();

                if (guess < targetNumber) {
                    System.out.println("Too low! Try again.");
                } else if (guess > targetNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed the correct number: " + targetNumber);
                    return attemptsLeft;  
                }

                attemptsLeft--;  
                System.out.println("Attempts left: " + attemptsLeft);
            }

            System.out.println("Sorry, you're out of attempts. The correct number was " + targetNumber + ".");
        }

        return 0;  
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int totalRounds = 0;
            int totalScore = 0;
            String playAgain = "y";

            while (playAgain.equalsIgnoreCase("y")) {
                totalRounds++;
                int remainingAttempts = playRound();
                if (remainingAttempts > 0) {
                    totalScore += remainingAttempts;  
                }
                
                System.out.print("Do you want to play again? (y/n): ");
                playAgain = scanner.next();
            }

            System.out.println("\nGame Over! You played " + totalRounds + " rounds.");
            System.out.println("Your total score is: " + totalScore + " points.");
        }
        System.out.println("Thanks for playing!");
    }
}