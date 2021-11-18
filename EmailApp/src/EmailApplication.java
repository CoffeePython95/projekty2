/*
overview of requirements:
create email accounts for new hires
1. generate email with syntax: firstname.lastname@department.company.com
2. determine the department (sales, development, accounting), if none leave blank
3. generate random string for password
4. have set methods to change password, set mailbox capacity, and define alternate email address
5. have get methods to display name, email and mailbox capacity
 */

public class EmailApplication {
    public static void main(String[] args) {
        EmailGen em1 = new EmailGen("Jan", "Kowalski");
        EmailGen em2 = new EmailGen("Adam", "Nowak", "dev");

        System.out.println(em1);
        System.out.println(em1.getAltEmail());
        em1.setAltEmail("genericEmail@abc.xyz");
        System.out.println(em1.getAltEmail());
        System.out.println(em1.getEmail());
        System.out.println(em1.getName());

        System.out.println();

        System.out.println(em2);
        System.out.println(em2.getMailboxCap());
        em2.setMailboxCap(75);
        System.out.println(em2.getMailboxCap());
        em2.changePassword("qwerty123");
        System.out.println(em2.getPassword());
    }
}
