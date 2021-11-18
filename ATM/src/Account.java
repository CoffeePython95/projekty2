import java.util.ArrayList;

public class Account {

    /**
     * designation of the account
     */
    private String name;
    /**
     * universally unique identifier of account
     */
    final private String uuid;
    /**
     * User linked to this account
     */
    final private User owner;
    /**
     * list of transactions linked to this account
     */
    final private ArrayList<Transaction> transactions;


    /**
     * Constructor for creating new account object
     * @param name designation of account
     * @param owner user holding this account
     * @param bank  bank that creates this account
     */
    public Account(String name, User owner, Bank bank) {
        // setting accounts designation and owner
        this.name = name;
        this.owner = owner;

        // generating new UUID
        this.uuid = bank.getNewAccountUUID();

        // create empty list of transactions
        this.transactions = new ArrayList<>();

        // add account to bank's and user's account's list
        owner.addAccount(this);
        bank.addAccount(this);
    }


    /**
     * Method for getting account's UUID
     * @return UUID
     */
    public String getAccountUUID() {
        return this.uuid;
    }

    /**
     * Method for getting summary line of account
     * @return String summary line
     */
    public String getSummary() {
        // getting account's balance
        double balance = this.getBalance();

        // formatting summary line if balance is negative
        if (balance >= 0) {
            return String.format("%s : $%.02f : %s", this.uuid, this.getBalance(), this.name);
        } else {
            return String.format("%s : $(%.02f) : %s", this.uuid, this.getBalance(), this.name);
        }
    }

    /**
     * Method for calculation account's balance
     * @return account's balance
     */
    public double getBalance() {
        double balance = 0;
        for (Transaction transaction: this.transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    /**
     * Method for printing out transaction history for given account
     */
    public void printTransactionHistory() {
        System.out.printf("\nTransaction history for account %s\n", this.name);
        for (int i = this.transactions.size() - 1; i >=0; i--) {
            System.out.println(this.transactions.get(i).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * Method for creating new transaction and saving it in transactions list
     * @param amount how much is being transferred
     * @param note memo for transaction
     */
    public void addTransaction(double amount, String note) {
        // create new transaction and add it to list of transactions
        this.transactions.add(new Transaction(amount, this, note));
    }
}
