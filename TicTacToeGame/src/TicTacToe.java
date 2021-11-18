import java.util.Scanner;

public class TicTacToe {

    String[][] fields;
    boolean playersTurn;


    public TicTacToe() {
        gameLoop();
    }

    private void gameLoop() {
        fieldsInit();
        playersTurn = true;
        do {
            makeMove();
            drawBoard();
        } while (!isGameOver() && isAnyFieldEmpty());
        System.out.println("\nGame ended!");
    }


    private void fieldsInit() {
        fields = new String[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                this.fields[i][j] = " ";
            }
        }
        drawBoard();
    }

    private void drawBoard() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.print(this.fields[j][i]);
                if (j < 2) {
                    System.out.print("|");
                }
            }
            if (i < 2) {
                System.out.println("\n-+-+-");
            }
        }
        System.out.println();
    }

    private void makeMove() {
        if (playersTurn) {
            playerMove();
        } else {
            computerMove();
        }
        playersTurn = ! playersTurn;
    }

    private void playerMove() {
        System.out.println("Input column and row to place 'x'");
        System.out.print("Column: ");
        int col = getInput() - 1;
        System.out.print("Row: ");
        int row = getInput() - 1;
        System.out.println();
        if(inBounds(col, row) && isEmpty(col, row)) {
            this.fields[col][row] = "X";
        } else {
            System.out.println("Illegal move, try again!");
            drawBoard();
            playerMove();
        }
    }

    private void computerMove() {

        int i = (int)(Math.random()*3);
        int j = (int)(Math.random()*3);
        if(this.fields[i][j].equals(" ")) {
            System.out.println("\nOpponent placed 'o' at column number " + (i + 1 ) + " row number " + (j + 1));
            this.fields[i][j] = "o";
        } else {
            computerMove();
        }
    }

    private boolean isEmpty(int x, int y) {
        return this.fields[x][y].equals(" ");
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && x <= 2 && y >= 0 && y <= 2;
    }

    private boolean isAnyFieldEmpty() {
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.fields[i][j].equals(" ")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isGameOver() {
        for (int i = 0; i < 3; i++) {
            if (!this.fields[i][0].equals(" ") && this.fields[i][0].equals(this.fields[i][1]) && this.fields[i][0].equals(this.fields[i][2])) {
                return true;
            } else if (!this.fields[0][i].equals(" ") && this.fields[0][i].equals(this.fields[1][i]) && this.fields[0][i].equals(this.fields[2][i])) {
                return true;
            }
        }
        return (!this.fields[0][0].equals(" ") && this.fields[0][0].equals(this.fields[1][1]) && this.fields[0][0].equals(this.fields[2][2]) ||
                !this.fields[0][2].equals(" ") && this.fields[0][2].equals(this.fields[1][1]) && this.fields[0][2].equals(this.fields[2][0]));
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
