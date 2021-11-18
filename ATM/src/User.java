import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {

    /**
     * first name of user
     */
    final private String firstname;
    /**
     * last name of user
     */
    final private String lastName;
    /**
     * universally unique identifier of user
     */
    final private String uuid;
    /**
     * pin hashed with MD5
     */
    private byte[] pinHashed;
    /**
     * list of accounts user is linked to this user
     */
    final private ArrayList<Account> accounts;


    /**
     * Constructor for creating new user object
     * @param firstName first name of user
     * @param lastName last name of user
     * @param pin user's PIN number
     * @param bank bank user is customer of
     */
    public User(String firstName, String lastName, String pin, Bank bank) {
        // setting users name and surname
        this.firstname = firstName;
        this.lastName = lastName;

        // pin isn't stored in its normal form, hashed for safety
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHashed = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        // generating new uuid
        this.uuid = bank.getNewUserUUID();

        // create empty list of accounts
        this.accounts = new ArrayList<>();

        // log msg
        System.out.printf("New user %s %s with ID %s created.\n", this.firstname, this.lastName, this.uuid);
    }


    /**
     * Method for adding account to user account's list
     * @param account account to add to list
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    /**
     * Method for getting user's first name
     * @return firstName
     */
    public String getFirstName() {
        return this.firstname;
    }

    /**
     * Method for getting user's UUID
     * @return UUID
     */
    public String getUserUUID() {
        return this.uuid;
    }

    /**
     * Method for checking if given pin is correct
     * @param pin given pin
     * @return true if given pin hashed is same as saved one, false if not
     */
    public boolean validatePin(String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(pin.getBytes()),pinHashed);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    /**
     * Method for printing out user's accounts details
     */
    public void printAccountsSummary() {
        System.out.printf("\n%s's accounts summary\n", this.firstname);
        for (int i = 0; i < this.accounts.size(); i++) {
            System.out.printf("\t%d) %s\n", (i + 1), this.accounts.get(i).getSummary());
        }
    }

    /**
     * Method for checking how many accounts user has
     * @return size of accounts list
     */
    public int numAccounts() {
        return this.accounts.size();
    }

    /**
     * Method for printing out transaction history of given account
     * @param accountIndex index of account in accounts list
     */
    public void printTransactionHistory(int accountIndex) {
        this.accounts.get(accountIndex).printTransactionHistory();
    }

    /**
     * Method for getting balance of account with given index
     * @param accountIndex index of account for accounts list
     * @return balance of given account
     */
    public double getAccountBalance(int accountIndex) {
        return accounts.get(accountIndex).getBalance();
    }

    /**
     * Method of getting UUID of account with given index in accounts list
     * @param accountIndex index of account in accounts list
     * @return UUID of account
     */
    public String getAccountUUID(int accountIndex) {
        return this.accounts.get(accountIndex).getAccountUUID();
    }

    /**
     * Method for adding transaction
     * @param accountIndex index of account in accounts list
     * @param amount how much is being transferred
     * @param note memo for transaction
     */
    public void addAccountTransaction(int accountIndex, double amount, String note) {
        this.accounts.get(accountIndex).addTransaction(amount, note);
    }
}
