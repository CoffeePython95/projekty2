import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hangman {

    private int maxWordLength;
    private int tries;
    private List<Character> guesses;
    private String word;


    public Hangman() throws FileNotFoundException {
        gameLoop();
    }

    public Hangman(int maxWordLength, int tries) throws FileNotFoundException {
        this.maxWordLength = maxWordLength;
        this.tries = tries;
        gameLoop();
    }

    // loop containing whole game
    private void gameLoop() throws FileNotFoundException {
        listInit();
        if (tries == 0) {
            tries = 6;
        }
        newWord();
        System.out.println(word);
        do {
            System.out.println("Remaining tries: " + tries);
            printGuesses();
            inputLetter();
        } while (!isGameOver() && tries > 0);
        if (tries != 0) {
            System.out.println(word);
            System.out.println("\nCongrats, you won!");
        }
        System.out.println("\nSorry, you didn't win this time. Secret word was: " + word);
    }

    // initialize list with commas, slashes and so on
    private void listInit() {
        guesses = new ArrayList<>();
        guesses.add('-');
        guesses.add(',');
        guesses.add('.');
        guesses.add('/');
        guesses.add('&');
        guesses.add(':');
        guesses.add('\'');
    }

    // get letter from player, remove 1 chance if letter not in word
    private void inputLetter() {
        System.out.println("Input letter: ");
        char guess = new Scanner(System.in).next().toLowerCase().charAt(0);
        guesses.add(guess);

        boolean isIn = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                isIn = true;
                break;
            }
        }
        if (!isIn) {
            tries--;
        }
    }

    // select word from dictionary
    private void newWord() {
        Scanner input = null; //text file with words is in same folder as class file
        try {
            input = new Scanner(new File(Hangman.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/words.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error, caught FileNotFoundException");
            e.printStackTrace();
            System.exit(1);
        }

        List<String> words = new ArrayList<>();

        while (input.hasNext()) {
            words.add(input.nextLine());
        }

        if (maxWordLength == 0) {
            this.word = words.get((int) (Math.random() * words.size()));
        } else {
            do {
                this.word = words.get((int) (Math.random() * words.size()));
            } while (!(this.word.length() < maxWordLength));
        }
    }

    // print out players guesses
    private void printGuesses() {
        for (int i = 0; i < word.length(); i++) {
            if (guesses.contains(word.toLowerCase().charAt(i))) {
                System.out.print(word.charAt(i));
            } else {
                System.out.print("_");
            }
        }
        System.out.println();
    }

    private boolean isGameOver (){
        for (int i = 0; i < word.length(); i++) {
            if (!guesses.contains(word.toLowerCase().charAt(i)) || tries == 0) {
                return false;
            }
        }
        return true;
    }
}
