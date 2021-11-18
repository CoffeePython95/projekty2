import java.util.Scanner;

public class YouGuess {

    public YouGuess() {
        game(0, 100);
    }

    private void game(int rangeStart, int rangeEnd) {
        int tries = 0;

        int number = (int)(Math.random() * rangeEnd) + 1 + rangeStart;
        System.out.println(number);
        System.out.println("Guess a number in range between: " + rangeStart + " - " + rangeEnd);
        int guess;

        do {
            guess = getInput();
            tries++;
            if (number < guess) {
                System.out.println("Secret number is smaller than " + guess);
            } else if (number > guess) {
                System.out.println("Secret number is bigger than " + guess);
            } else {
                System.out.println("You guessed correct number: " + number + ", it took you " + tries + " attempt(s)!");
            }

        } while (number != guess);
        System.out.println("Congrats!");
    }

    private int getInput() {
        try {
            return new Scanner(System.in).nextInt();
        } catch (Exception e ) {
            System.out.print("Incorrect input! Try again: ");
            return getInput();
        }
    }

}
