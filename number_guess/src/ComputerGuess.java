import java.util.Scanner;

public class ComputerGuess {

    public ComputerGuess() {
        game(0, 100);
    }

    private void game(int rangeStart, int rangeEnd) {
        System.out.println("Think of a number in range: " + (rangeStart + 1) + " - " + rangeEnd + "\n");
        gameLoop: do {
            int guess = (int) (Math.random() * (rangeEnd - rangeStart) + 1 + rangeStart);
            System.out.println("Is it your number: " + guess + "?");
            System.out.println("Y - yes; B - no, my secret number is bigger; S - no, my secret number is smaller\nif you don't give any of these answers i'll guess again ");
            switch(new Scanner(System.in).next()) {
                case "y", "Y":
                    break gameLoop;
                case "s", "S":
                    if (guess < rangeEnd) {
                        rangeEnd = guess;
                    }
                    break;
                case "b", "B":
                    if (guess > rangeStart) {
                        rangeStart = guess;
                    }
                    break;
                default:
            }
        } while (true);
    }

}
