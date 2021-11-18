import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Would you rather:\n1. Guess a number\n2. Make computer guess a number\ndefault: Not play at all");
        switch (getInput()) {
            case 1 -> new YouGuess();
            case 2 -> new ComputerGuess();
            default -> {
            }
        }
        System.out.println("Goodbye!");
    }

    private static int getInput() {
        try {
            return new Scanner(System.in).nextInt();
        } catch (Exception e ) {
            System.out.print("Incorrect input! Try again: ");
            return getInput();
        }
    }

}
