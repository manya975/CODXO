package p1;

import java.util.Random;
import java.util.Scanner;

public class Guess {
	    public static void main(String[] args) {
	        Scanner input = new Scanner(System.in);

	        Random rand = new Random();

	        // Define the lower and upper bounds for the random number
	        int lowerBound = 1;
	        int upperBound = 100;

	        // Generate a random number within the specified range
	        int numberToGuess = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;

	        // Initialize the number of tries
	        int numberOfTries = 0;

	        // Display a welcome message and the game's instructions
	        System.out.println("Welcome to the Number Guessing Game!");
	        System.out.println("I'm thinking of a number between " + lowerBound + " and " + upperBound + ".");

	        // Start a loop for the game
	        while (true) {
	            // Prompt the user to guess the number
	            System.out.print("Guess the number: ");
	            int userGuess = input.nextInt();

	            // Increment the number of tries
	            numberOfTries++;

	            // Check if the user's guess is correct
	            if (userGuess == numberToGuess) {
	                // If the guess is correct, display a win message with the number of tries and exit the loop
	                System.out.println("Congratulations! You guessed the number in " + numberOfTries + " tries.");
	                break;
	            } else if (userGuess < numberToGuess) {
	                // If the guess is too low, provide feedback to try a higher number
	                System.out.println("Try a higher number.");
	            } else {
	                // If the guess is too high, provide feedback to try a lower number
	                System.out.println("Try a lower number.");
	            }
	        }

	        // Close the Scanner object to release system resources
	        input.close();
	    }
	}

