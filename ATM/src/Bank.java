import java.util.ArrayList;

public class Bank {

    /**
     * banks name
     */
    final private String name;
    /**
     * list of users
     */
    final private ArrayList<User> users;
    /**
     * list of accounts
     */
    final private ArrayList<Account> accounts;


    /**
     * Constructor for creating new bank object with empty list of users & accounts
     * @param name name of the bank
     */
    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }


    /**
     * Method for generating UUID for user
     * @return UUID
     */
    public String getNewUserUUID() {
        // setting user UUID length, creating stringBuilder to generate it, creating boolean to check uniqueness of UUID later
        int len = 9;
        StringBuilder sb = new StringBuilder(len);
        boolean unique;

        // looping until generated id is unique
        do {
            unique = true;
            // generating new UUID
            for (int i = 0; i < len; i++) {
                sb.append((int)(Math.random() * 10));
            }
            // checking UUID's uniqueness
            for (User user: this.users) {
                if (sb.toString().equals(user.getUserUUID())) {
                    unique = false;
                    break;
                }
            }
        } while (!unique);

        return sb.toString();
    }

    /**
     * Method for generating UUID for account
     * @return UUID
     */
    public String getNewAccountUUID() {
        // setting account UUID length, creating stringBuilder to generate it, creating boolean to check uniqueness of UUID later
        int len = 10;
        StringBuilder sb = new StringBuilder(len);
        boolean unique;

        // looping until generated id is unique
        do {
            unique = true;
            // generating new UUID
            for (int i = 0; i < len; i++) {
                sb.append((int)(Math.random() * 10));
            }
            // checking UUID's uniqueness
            for (Account account: this.accounts) {
                if (sb.toString().equals(account.getAccountUUID())) {
                    unique = false;
                    break;
                }
            }
        } while (!unique);

        return sb.toString();
    }

    /**
     * Method for adding account to bank account's list
     * @param account account to add to list
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    /**
     * Method for creating new user with 1 default account for him
     * @param firstName – first name of user
     * @param lastName – last name of user
     * @param pin – user's PIN number
     * @return new user
     */
    public User addUser(String firstName, String lastName, String pin) {
        // creating new User with given parameters
        User user = new User(firstName, lastName, pin, this);
        this.users.add(user);

        // created 1 default account for user
        new Account("Savings", user, this);

        return user;
    }

    /**
     * Method for getting user with associated UUID and pin if they match
     * @param userID UUID of user trying to login
     * @param pin pin of user trying to login
     * @return user/ null
     */
    public User login(String userID, String pin) {
        //check if there is user with given UUID and pin and return him
        for (User user: users) {
            if(user.getUserUUID().equals(userID) && user.validatePin(pin)) {
                return user;
            }
        }
        // if there is no user with given params return null
        return null;
    }

    /**
     * Method for getting bank's name
     * @return bank's name
     */
    public String getName() {
        return  this.name;
    }

}
