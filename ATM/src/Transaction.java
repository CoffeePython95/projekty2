import java.util.Date;

public class Transaction {

    /**
     * reminder of transaction
     */
    private String note;
    /**
     * how much money in transaction
     */
    final private double amount;
    /**
     * timestamp of transaction
     */
    final private Date timestamp;
    /**
     * account linked to transaction
     */
    final private Account account;

    /**
     * Constructor for creating new Transaction object with no note
     * @param amount how much money in transaction
     * @param account account linked to transaction
     */
    public Transaction(double amount, Account account) {
    // setting transactions amount, account and timestamp
        this.amount = amount;
        this.account = account;
        this.timestamp = new Date();
        this.note = "";
    }

    /**
     * Constructor for creating new Transaction object with note
     * @param amount how much money in transaction
     * @param account account linked to transaction
     * @param note reminder of transaction
     */
    public Transaction(double amount, Account account, String note) {
        // using 2 argument constructor from above to set transactions amount, account and timestamp, adding note
        this(amount, account);
        this.note = note;
    }

    /**
     * Method for getting transaction's amount
     * @return transaction's amount
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Method for getting string summarizing transaction
     * @return summary line of transaction
     */
    public String getSummaryLine() {
        if (this.amount >= 0) {
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.note);
        } else {
            return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), this.amount, this.note);
        }
    }
}

