import java.util.Scanner;

public class BankAcc {

    private final String name;
    private double balance;
    private double previousTransaction;

    BankAcc(String name) {
        this.name = name;
        showMenu();
    }

    void showMenu() {

        int option;

        do {
            System.out.println("=================");
            System.out.println("Hello "+ name + "! Please choose option from below: ");
            System.out.println("\t1. Check balance\n\t2. Deposit\n\t3. Withdraw\n\t4. Check last transaction\n\t0. Exit");
            System.out.println("=================");

            option = getInput();

            switch (option) {
                case 1 -> showBalance();
                case 2 -> {
                    System.out.print("How much do you wish to deposit: ");
                    deposit(getInput());
                }
                case 3 -> {
                    System.out.print("How much do you wish to withdraw: ");
                    withdraw(getInput());
                }
                case 4 -> showLastTransaction();
                case 0 -> System.out.println("Farewell!");
                default -> System.out.println("Incorrect input, try again.");
            }

        } while (option != 0);

        System.out.println("Thank you for staying with us!");
    }

    void showBalance() {
        System.out.println("\nBalance: $" + this.balance +"\n");
    }

    void deposit(double amount) {
        if (isAmountNot0(amount)) {
            this.balance += amount;
            this.previousTransaction = amount;
        } else {
            System.out.println("Invalid transaction!");
        }
    }

    void withdraw(double amount) {
        if (isAmountNot0(amount) && canUserWithdraw(amount)) {
            this.balance -= amount;
            this.previousTransaction = -amount;
        } else {
            System.out.println("Invalid transaction!");
        }
    }

    void showLastTransaction() {
        if (previousTransaction > 0 ) {
            System.out.println("Deposited: $" + previousTransaction);
        } else if (previousTransaction < 0) {
            System.out.println("Withdrawn: $" + Math.abs(previousTransaction));
        } else {
            System.out.println("No record of previous transaction");
        }
    }

    private boolean isAmountNot0(double amount) {
        return amount > 0;
    }

    private boolean canUserWithdraw(double amount) {
        return this.balance >= amount;
    }

    private int getInput() {
        try {
            System.out.print("Input desired value: ");
            return new Scanner(System.in).nextInt();
        } catch (Exception e ) {
            System.out.println("Incorrect input!");
            return getInput();
        }
    }

}
