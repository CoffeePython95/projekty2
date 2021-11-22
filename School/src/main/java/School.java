import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class School {

    public School() {
        manageStudents();
    }

    /**
     * Method for managing people assigned to student group
     */
    public void manageStudents() {
        int selector;
        do {
            System.out.println("Student group manager: ");
            System.out.println("\t1. Check student data");
            System.out.println("\t2. Add student");
            System.out.println("\t0. Exit");
            System.out.print("Select option: ");
            selector = getInt();
            switch (selector) {
                case 1 -> checkStudent();
                case 2 -> addStudent();
                default -> System.out.println();
            }
        } while(selector != 0);
    }

    /**
     * Method for getting student id from user to check if it exists in DB
     */
    public void checkStudent() {
        int id;
        System.out.print("\nInput students ID: ");
        id = getInt();

        StudentDAO dao = new StudentDAO();
        Student student = dao.getStudent(id);

        DBConnector.connect();
        if(student == null) {
            System.out.println("No student with matching id found");
        } else {
            updateStudent(student);
        }
        System.out.println();
    }

    /**
     * Method for managing current student record
     */
    public void updateStudent(Student student) {
        int selector;
        do {
            System.out.println(student);
            System.out.println("\nStudent manager: ");
            System.out.println("\t1. Change students grade");
            System.out.println("\t2. Change students email");
            System.out.println("\t5. Delete student");
            System.out.println("\t0. Exit");
            System.out.print("Select option: ");
            selector = getInt();
            switch (selector) {
                case 1 -> changeGrade(student);
                case 2 -> changeEmail(student);
                case 5 -> deleteStudent(student);
                default -> System.out.println();
            }
        } while(selector != 0 && selector != 5);
    }

    /**
     * Method for changing students grade
     * @param student Student object
     */
    public void changeGrade(Student student) {
        System.out.print("Input new email address: ");
        student.setGrade(getInt());
        new StudentDAO().updateStudent("grade", student.getGrade(),  student);
    }

    /**
     * Method for changing students email
     * @param student Student object
     */
    public void changeEmail(Student student) {
        System.out.print("Input new email address: ");
        student.setEmail(new Scanner(System.in).next());
        new StudentDAO().updateStudent("email", student.getEmail(),  student);
    }

    /**
     * Method for removing student from database
     */
    public void deleteStudent(Student student) {
        System.out.print("Operation is irreversible, type 'YES' to proceed: ");
        if(new Scanner(System.in).next().equals("YES")) {
            new StudentDAO().deleteStudent(student);

        }
    }

    /**
     * Method for adding student to database
     */
    public void addStudent() {
        String finished;
        Student student = new Student();
        System.out.println("\nUser creation wizard");

        student.setId(studentIdGen());

        do {
            System.out.print("Input first name: ");
            student.setFirstName(new Scanner(System.in).next());

            System.out.print("Input last name: ");
            student.setLastName(new Scanner(System.in).next());

            System.out.print("Input gender (M/F): ");
            student.setGender(new Scanner(System.in).next());

            System.out.print("Input date of birth (yyyy-M-d): ");
            student.setDateOfBirth(getDate());

            System.out.print("Input grade: ");
            student.setGrade(getInt());

            System.out.println(student);
            System.out.println("Input user into database (y/n)?");
            finished = new Scanner(System.in).next();

        } while (!finished.equalsIgnoreCase("y"));

        new StudentDAO().addStudent(student);
    }

    /**
     * Method for generating students id
     * @return new id
     */
    public int studentIdGen() {
        int id;
        id = 10000 + (int)(Math.random() * 9999 + 1);

        if(new StudentDAO().doesIdExist(id)) {
            studentIdGen();
        }
        return id;
    }


    /**
     * Method for getting int from user
     * @return user inputted int
     */
    private int getInt() {
        try {
            return new Scanner(System.in).nextInt();
        } catch (Exception e) {
            System.out.print("Input not recognized, try again: ");
            return getInt();
        }
    }

    /**
     * Method for getting date from user
     * @return user inputted date
     */
    private LocalDate getDate() {

        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
            return LocalDate.parse(new Scanner(System.in).next(), dateFormat);
        } catch (Exception e) {
            System.out.print("Input not recognized, try again: ");
            return getDate();
        }
    }
}
