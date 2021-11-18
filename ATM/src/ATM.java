// based on TechLiterate yt video on this topic with fixes and changes

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        // new scanner for input
        Scanner input = new Scanner(System.in);

        // new bank object
        Bank bank = new Bank("Thieves co");

        // adding user
        User user1 = bank.addUser("Jan", "Kowalski", "0000");

        // adding account for user
        new Account("Spendings", user1, bank);

        // ATM's unending loop
        User curUser;
        while(true) {
            // stay in login until successful login
            curUser = ATM.mainMenuPrompt(bank, input);

            // stay in main menu until user quits
            ATM.printUserMenu(curUser, input);
        }
    }


    /**
     * Method for printing out ATM login menu
     * @param bank bank in which user is registered
     * @param input input for login data
     * @return authenticated user
     */
    public static User mainMenuPrompt(Bank bank, Scanner input) {
        // initialized variables for login
        String userID;
        String pin;
        User authUser;

        // loop and get from user UUID and pin until correct
        do {
            System.out.printf("\nWelcome to %s\n", bank.getName());
            System.out.print("Enter user ID: ");
            userID = input.nextLine();
            System.out.print("Enter pin number: ");
            pin = input.nextLine();

            // checking if data is correct
            authUser = bank.login(userID, pin);
            if(authUser == null) {
                System.out.println("Incorrect login data, try again.");
            }
        } while(authUser == null);
        return authUser;
    }

    /**
     * Method for printing out User menu and choosing further options
     * @param user current user
     * @param input input for selecting menu options
     */
    public static void printUserMenu(User user, Scanner input) {
        // print summary of user's accounts
        user.printAccountsSummary();

        // init
        int choice;

        // user menu
        do {
            System.out.printf("\nWelcome %s, what would you like to do?\n", user.getFirstName());
            System.out.println("\t1) Show account transaction history");
            System.out.println("\t2) Withdraw");
            System.out.println("\t3) Deposit");
            System.out.println("\t4) Transfer");
            System.out.println("\t5) Quit");

            System.out.print("\nInput choice: ");
            choice = input.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (choice < 1 || choice > 5);

        switch (choice) {
            case 1 -> ATM.showTransactionHistory(user, input);
            case 2 -> ATM.withdraw(user, input);
            case 3 -> ATM.deposit(user, input);
            case 4 -> ATM.transfer(user, input);
            case 5 -> input.nextLine();
        }
        // display menu again if user wants to
        if (choice != 5) {
            ATM.printUserMenu(user, input);
        }
    }

    /**
     * Method for displaying transaction history of account user selects
     * @param user current user
     * @param input input for selecting menu options
     */
    private static void showTransactionHistory(User user, Scanner input) {
        int account;

        do {
            System.out.printf("Enter the number (1-%d) of account whose transactions you wish to check: ", user.numAccounts());
            account = input.nextInt() - 1;
            if(account < 0 || account >= user.numAccounts()) {
                System.out.println("Invalid choice. Please try again.");
            }
        } while(account < 0 || account >= user.numAccounts());

        // display transaction history
        user.printTransactionHistory(account);
    }

    /**
     * Method for withdrawing money from account
     * @param user current user
     * @param input input for selecting menu options
     */
    private static void withdraw(User user, Scanner input) {
        // account number, amount of transfer and how much sender has on account with memo
        int account;
        double amount;
        double accountBalance;
        String note;

        // get account and its balance
        do {
            user.printAccountsSummary();
            System.out.printf("\nEnter the number (1-%d) of account you wish to withdraw from: ", user.numAccounts());
            account = input.nextInt() - 1;
            if(account < 0 || account >= user.numAccounts()) {
                System.out.println("Invalid choice. Please try again.");
            }
        } while(account < 0 || account >= user.numAccounts());
        accountBalance = user.getAccountBalance(account);

        // get amount to transfer
        do {
            System.out.printf("Specify amount to withdraw (max $%.02f): $", accountBalance);
            amount = input.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than 0.");
            }else if (amount > accountBalance) {
                System.out.printf("Amount can't be greater than account's balance (max $%.02f)\n", accountBalance);
            }
        } while (amount <= 0 || amount > accountBalance);
        input.nextLine();

        // get user to write note
        System.out.print("Enter note: ");
        note = input.nextLine();

        // withdraw funds
        user.addAccountTransaction(account, -1 * amount, note);
    }

    /**
     * Method of depositing money to account
     * @param user current user
     * @param input input for selecting menu options
     */
    private static void deposit(User user, Scanner input) {
        // account number, amount of transfer and how much sender has on account with memo
        int account;
        double amount;
        String note;

        // get account
        do {
            user.printAccountsSummary();
            System.out.printf("\nEnter the number (1-%d) of account you wish to deposit to: ", user.numAccounts());
            account = input.nextInt() - 1;
            if(account < 0 || account >= user.numAccounts()) {
                System.out.println("Invalid choice. Please try again.");
            }
        } while(account < 0 || account >= user.numAccounts());

        // get amount to transfer
        do {
            System.out.print("Specify amount to deposit: $");
            amount = input.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than 0.");
            }
        } while (amount <= 0);
        input.nextLine();

        // get user to write note
        System.out.print("Enter note: ");
        note = input.nextLine();

        // withdraw funds
        user.addAccountTransaction(account, amount, note);
    }

    /**
     * Method for transferring money between accounts
     * @param user current user
     * @param input input for selecting menu options
     */
    private static void transfer(User user, Scanner input) {
        // number of sender and receiver accounts of transfer, amount of transfer and how much sender has on account
        int fromAcc;
        int toAcc;
        double amount;
        double accountBalance;

        // get sender account and its balance
        do {
            user.printAccountsSummary();
            System.out.printf("\nEnter the number (1-%d) of account you wish to transfer from: ", user.numAccounts());
            fromAcc = input.nextInt() - 1;
            if(fromAcc < 0 || fromAcc >= user.numAccounts()) {
                System.out.println("Invalid choice. Please try again.");
            }
        } while(fromAcc < 0 || fromAcc >= user.numAccounts());
        accountBalance = user.getAccountBalance(fromAcc);

        // get receiver account
        do {
            System.out.printf("Enter the number (1-%d) of account you wish to transfer to: ", user.numAccounts());
            toAcc = input.nextInt() - 1;
            if(toAcc < 0 || toAcc >= user.numAccounts()) {
                System.out.println("Invalid choice. Please try again.");
            }
        } while(toAcc < 0 || toAcc >= user.numAccounts());

        // get amount to transfer
        do {
            System.out.printf("Specify amount to transfer (max $%.02f): $", accountBalance);
            amount = input.nextDouble();
            if (amount <= 0) {
                System.out.println("Amount must be greater than 0.");
            }else if (amount > accountBalance) {
                System.out.printf("Amount can't be greater than account's balance (max $%.02f)\n", accountBalance);
            }
        } while (amount <= 0 || amount > accountBalance);

        // complete transfer
        user.addAccountTransaction(fromAcc, -1 * amount, String.format("Transfer to account %s", user.getAccountUUID(toAcc)));
        user.addAccountTransaction(toAcc, amount, String.format("Transfer from account %s", user.getAccountUUID(fromAcc)));
    }
}

