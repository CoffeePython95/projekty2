import java.util.Scanner;

public class EmailGen {

    final private String firstname;
    final private String lastname;
    private String email;
    private String password;
    private String department;

    private String altEmail;
    private float mailboxCap = 50;

    EmailGen(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = setDepartment();
        generatePassword();
        generateEmail();
    }

    EmailGen(String firstname, String lastname, String department) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        generatePassword();
        generateEmail();
    }


    public String getName() {
        return this.firstname + " " + this.lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setAltEmail(String altEmail) {
        this.altEmail = altEmail;
    }

    public String getAltEmail() {
        if (altEmail != null) {
            return this.altEmail;
        }
        return "Alternate email not added";
    }

    public void setMailboxCap(int mailboxCap) {
        this.mailboxCap = mailboxCap;
    }

    public float getMailboxCap() {
        return mailboxCap;
    }

    public String setDepartment() {
        Scanner input = new Scanner(System.in);
        System.out.println("Select department for hire:\n\tsales: sales\n\tdev: development\n\taccounting: acc\n\tnone: no department");
        System.out.print("Your input: ");

        loop1: while (true) {
            String dep = input.next();
            switch (dep) {
                case "sales":
                    this.department = "sales";
                    break loop1;
                case "dev":
                    this.department = "dev";
                    break loop1;
                case "acc":
                    this.department = "acc";
                    break loop1;
                case "none":
                    this.department = "";
                    break loop1;
                default:
                    System.out.println("Input not recognized, try again!");
                    System.out.print("Your input: ");
                    break;
            }
        }

        return department;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    private void generatePassword() {
        String characters = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpRrSsTtUuWwVvXxYyZz1234567890!@#$%";
        int passwordLength = 12;
        StringBuilder password = new StringBuilder(passwordLength);

        for (int i = 0; i < passwordLength; i++) {
            password.append(characters.charAt((int)(Math.random() * characters.length())));
        }
        this.password = password.toString();
    }

    private void generateEmail() {
        if(!this.department.equals("")) {
            this.email = this.firstname.toLowerCase() + "." + this.lastname.toLowerCase() + "@" + this.department.toLowerCase() + ".company.com";
        } else {
            this.email = this.firstname.toLowerCase() + "." + this.lastname.toLowerCase() + "@company.com";
        }
    }


    public String toString() {
        return this.firstname + " " + this.lastname + "\nEmail: " + this.email + "\nPassword: " + this.password;
    }

}
