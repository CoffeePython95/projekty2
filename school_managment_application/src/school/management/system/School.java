package school.management.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class School {
    /**
     * name of the school
     */
    final private String name;
    /**
     * money school earned
     */
    private static int moneyEarned;
    /**
     * money school spent
     */
    private static int moneySpent;
    /**
     * list of teachers
     */
    final private List<Teacher> teachers;
    /**
     * list of other employees
     */
    final private List<OtherEmployee> otherEmployees;
    /**
     * list of students
     */
    final private List<Student> students;


    /**
     * Constructor for creating school object, adds some default objects for testing purposes and open main menu of app
     * @param name name of school
     */
    public School(String name) {
        this.name = name;

        this.teachers = new ArrayList<>();
        this.otherEmployees = new ArrayList<>();
        this.students = new ArrayList<>();
        initialState();
        mainMenu();
    }


    /**
     * Method for displaying main menu
     */
    private void mainMenu() {
        int groupSelection;
        do {
            System.out.print("\n(Simple) " + this.name + " School Management System:" +
                    "\n\t1. Manage students\n\t2. Manage teachers\n\t3. Manage other employees\n\t4. Manage funds\n\t0. Exit\nInput: ");
            groupSelection = getInput();
            switch (groupSelection) {
                case 1 -> managePeople("students");
                case 2 -> managePeople("teachers");
                case 3 -> managePeople("otherEmployees");
                case 4 -> manageFunds();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Input not in allowed range, try again.");
            }
        } while (groupSelection != 0);
    }

    /**
     * Method for managing specific groups
     * @param group which group is being managed
     */
    private void managePeople(String group) {
        int selection;
        do {
            System.out.printf("\n\n%s:\n\t1. Search for person in %s group by ID number\n\t2. Add person to %s group list\n\t9. Change group\n\t0. Back\nInput: ", group.toUpperCase(), group, group);
            selection = getInput();
            switch (selection) {
                case 1 -> checkPerson(group);
                case 2 -> addPerson(group);
                case 9 -> group = changeGroup();
                case 0 -> {}
                default -> System.out.println("Input not recognized, try again.");
            }
        } while (selection != 0);

    }

    /**
     * Method for checking if given id matches person in group list
     * @param group group checked for matching id
     */
    private void checkPerson(String group) {
        System.out.printf("\nInput ID number of person belonging to %s group to see details: ", group);
        Person person = findPerson(getInput(), group);
        if (person == null) {
            int selection;
            do {
                System.out.println("\nNo person with matching ID found in group " + group);
                System.out.print("\t1. Try again\n\t9. Change group\n\t0. Back\nInput: ");
                selection = getInput();
                switch (selection) {
                    case 9:
                        changeGroup();
                    case 1:
                        checkPerson(group);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Input not recognized, try again.");
                }
            } while (selection != 0);
        } else {
            if (person.getClass().toString().equals("class school.management.system.Student")) {
                manageStudent((Student)person);
            } else if (person.getClass().toString().equals("class school.management.system.Teacher")) {
                manageTeacher((Teacher)person);
            } else {
                manageOtherEmployee((OtherEmployee)person);
            }
        }
    }

    /**
     * Method for changing group user browses
     * @return String with group name user selected
     */
    private String changeGroup() {
        do {
            System.out.print("\n\nChange group to:\n\t1. Students\n\t2. Teachers\n\t3. Other employees\nInput: ");
            switch(getInput()) {
                case 1:
                    return "students";
                case 2:
                    return "teachers";
                case 3:
                    return "otherEmployees";
                default:
                    System.out.print("Input not in allowed range, try again: ");
            }
        } while(true);
    }

    /**
     * Method for checking if person with given id exists and returns this person if true
     * @param id id of person user looks for
     * @param group which list is checked
     * @return person with matching id/ null
     */
    private Person findPerson(int id, String group) {
        switch (group) {
            case "students":
                for (Person person : students) {
                    if (person.getId() == id) {
                        return person;
                    }
                }
                break;
            case "teachers":
                for (Person person : teachers) {
                    if (person.getId() == id) {
                        return person;
                    }
                }
                break;
            case "otherEmployees":
                for (Person person : otherEmployees) {
                    if (person.getId() == id) {
                        return person;
                    }
                }
                break;
        }
        return null;
    }

    /**
     * Method for managing student
     * @param student student being managed
     */
    private void manageStudent(Student student) {
        int selection;
        studentLoop: do {
            System.out.println(student);
            System.out.println("\nMANAGE STUDENT\n\t1. Check grades\n\t2. Add grades\n\t3. Change grade\n\t4. Receive fees\n\t9. Remove from students list\n\t0. Back\nInput: ");
            selection = getInput();
            switch (selection) {
                case 1:
                    checkStudentsGrades(student);
                    break;
                case 2:
                    addStudentsGrades(student);
                    break;
                case 3:
                    System.out.print("\nGrade: " + student.getGrade());
                    System.out.print("\nChange grade to: ");
                    int grade = getInput();
                    student.setGrade(grade);
                    break;
                case 4:
                    System.out.println("\nCurrent fees: " + student.getFeesPaid() + "/" + student.getFeesTotal());
                    System.out.print("Fee received from student: $");
                    int fee = getInput();
                    student.payFees(fee);
                    break;
                case 9:
                    System.out.println("\nOperation is irreversible. Type 'yes' to continue.");
                    if (new Scanner(System.in).next().equalsIgnoreCase("yes")) {
                        String name = student.getName();
                        students.remove(student);
                        System.out.println("Student " + name + " removed from students list");
                        break studentLoop;
                    } else {
                        System.out.println("Operation aborted.");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Input not recognized, try again.");
            }
        } while (selection != 0);
    }

    /**
     * Method for checking student's grades
     * @param student student of which grades you want to check
     */
    private void checkStudentsGrades(Student student) {
        int index;

        do {
            System.out.println("\nSUBJECTS");
            (student).printSubjects();
            System.out.printf("\nEnter the number (1-%d) of subject you wish to check grades of: ", student.numSubjects());
            index = getInput() - 1;
            if (index < 0 || index >= student.numSubjects()) {
                System.out.println("Input not recognized, try again.");
            }
        } while(index < 0 || index >= student.numSubjects());

        student.printSubjectGrades(index);
    }

    /**
     * Method for adding grade to student's grades list
     * @param student student to which you want to add new grade
     */
    private void addStudentsGrades(Student student) {
        int index;
        int grade;

        do {
            System.out.println("\nSUBJECTS");
            (student).printSubjects();
            System.out.printf("\nEnter the number (1-%d) of subject you wish to add grade to: ", student.numSubjects());
            index = getInput() - 1;
            if (index < 0 || index >= student.numSubjects()) {
                System.out.println("Input not recognized, try again.");
            }
        } while(index < 0 || index >= student.numSubjects());

        do {
            System.out.print("Grade to add: ");
            grade = getInput();
            if(grade <= 0 || grade >6) {
                System.out.println("Input not recognized, try again: ");
            }
        } while(grade <= 0 || grade >6);
        student.addSubjectGrade(index, grade);
    }

    /**
     * Method for managing teacher
     * @param teacher teacher being managed
     */
    private void manageTeacher(Teacher teacher) {
        teacherLoop: do {
            System.out.println(teacher);
            System.out.println("\n\t1. Change salary\n\t9. Remove from workers list\n\t0. Back");
            switch (getInput()) {
                case 1:
                    System.out.println("Current salary: " + teacher.getSalary());
                    System.out.print("Change salary to: ");
                    int salary = getInput();
                    teacher.setSalary(salary);
                    break;
                case 9:
                    System.out.println("Operation is irreversible. Type 'yes' to continue.");
                    if (new Scanner(System.in).next().equalsIgnoreCase("yes")) {
                        String name = teacher.getName();
                        teachers.remove(teacher);
                        System.out.println("Worker " + name + " removed from students list");
                        break teacherLoop;
                    } else {
                        System.out.println("Operation aborted.");
                    }
                    break;
                case 0:
                    break teacherLoop;
                default:
                    System.out.println("Input not recognized, try again.");
            }
        } while (true);
    }

    /**
     * Method for managing otherEmployee
     * @param otherEmployee otherEmployee being managed
     */
    private void manageOtherEmployee(OtherEmployee otherEmployee) {
        otherEmployeeLoop: do {
            System.out.println("\n\t1. Change salary\n\t5. Remove from workers list\n\t0. Back");
            switch (getInput()) {
                case 1:
                    System.out.println("Current salary: " + otherEmployee.getSalary());
                    System.out.print("Change salary to: ");
                    int salary = getInput();
                    otherEmployee.setSalary(salary);
                    break;
                case 5:
                    System.out.println("Operation is irreversible. Type 'yes' to continue.");
                    if (new Scanner(System.in).next().equalsIgnoreCase("yes")) {
                        String name = otherEmployee.getName();
                        otherEmployees.remove(otherEmployee);
                        System.out.println("Worker " + name + " removed from students list");
                        break otherEmployeeLoop;
                    } else {
                        System.out.println("Operation aborted.");
                    }
                    break;
                case 0:
                    break otherEmployeeLoop;
                default:
                    System.out.println("Input not recognized, try again.");

            }
        } while (true);
    }

    /**
     * Method for adding new person to group list
     * @param group to which group person is added
     */
    private void addPerson(String group) {

        System.out.print("\nInput Name: ");
        String name = new Scanner(System.in).next();
        if (group.equals("students")) {
            int id;
            idGen: do {
                id = (int)(Math.random()*10000)+90000;
                for (Student student: students) {
                    if (student.getId() != id) {
                        System.out.println("Id: " + id);
                        break idGen;
                    }
                }
            } while (true);

            System.out.print("Input new grade: ");
            int grade = getInput();

            students.add(new Student(name, id, grade));

        } else if (group.equals("teachers")) {
            int id;
            idGen: do {
                id = (int)(Math.random()*10000)+10000;
                for (Teacher teacher: teachers) {
                    if (teacher.getId() != id) {
                        System.out.println("Id: " + id);
                        break idGen;
                    }
                }
            } while (true);

            System.out.print("Input new salary: ");
            int salary = getInput();

            teachers.add(new Teacher(name, id, salary));
        } else {
            int id;

            idGen: do {
                id = (int)(Math.random()*10000)+10000;
                for (OtherEmployee otherEmployee: otherEmployees) {
                    if (otherEmployee.getId() != id) {
                        System.out.println("Id: " + id);
                        break idGen;
                    }
                }
            } while (true);

            System.out.print("Input new salary: ");
            int salary = getInput();

            System.out.print("Input new employee occupation: ");
            String occupation = new Scanner(System.in).next();
            otherEmployees.add(new OtherEmployee(name, id, salary, occupation));
        }

    }

    /**
     * Method for managing schools funds
     */
    private void manageFunds() {
        fundsLoop: do {
            System.out.println("\n\nFUNDS MANAGEMENT\n\t1. Check income/expenses\n\t2. Pay salaries\n\t0. Back");
            switch (getInput()) {
                case 1:
                    System.out.println("\nTotal money earned: " + getMoneyEarned());
                    System.out.println("Total money spent: " + getMoneySpent());
                    System.out.println("Balance: " + (getMoneyEarned()-getMoneySpent()));
                    break;
                case 2:
                    if (teachers != null) {
                        for (Worker worker : teachers) {
                            worker.receiveSalary();
                        }
                    }
                    if (otherEmployees != null) {
                        for (Worker worker : otherEmployees) {
                            worker.receiveSalary();
                        }
                    }
                    break;
                case 0:
                    break fundsLoop;
                default:
                    System.out.println("Input not recognized, try again.");
            }
        } while (true);
    }

    /**
     * getter for moneyEarned of school
     * @return moneyEarned field of School class
     */
    public int getMoneyEarned() {
        return moneyEarned;
    }

    /**
     * getter for moneySpent of school
     * @return moneySpent field of School class
     */
    public int getMoneySpent() {
        return moneySpent;
    }

    /**
     * Method for increasing moneySpent field by income value
     * @param expenditure how much moneySpent field increases by
     */
    public static void updateMoneySpent(int expenditure) {
        moneySpent += expenditure;
    }

    /**
     * Method for increasing moneyEarned field by income value
     * @param income how much moneyEarned field increases by
     */
    public static void updateMoneyEarned(int income) {
        moneyEarned += income;
    }

    // Method that creates few Students and Workers for school at start for testing purposes
    private void initialState() {
        Student student1 = new Student("Steph", 10001,2);
        students.add(student1);
        students.add(new Student("Ellie", 10002,5));
        students.add(new Student("Thomas", 10003,3));
        students.add(new Student("Bart", 10004));
        students.add(new Student("Elliot", 10005,7));
        students.add(new Student("Mark", 10006,2));
        students.add(new Student("Elliot", 10007));
        students.add(new Student("Oliver", 10008,6));
        students.add(new Student("John", 10009,8));
        students.add(new Student("Oleg", 10010,4));

        teachers.add(new Teacher("Anna", 90001,2600));
        teachers.add(new Teacher("Jo", 90002,3700));
        teachers.add(new Teacher("Tim", 90003,3600));

        otherEmployees.add(new OtherEmployee("Tom", 80001, 1500, "janitor"));
        otherEmployees.add(new OtherEmployee("Sandra", 80002, 1400, "cook"));
        otherEmployees.add(new OtherEmployee("Ellen", 80003, 1300, "cleaner"));
        otherEmployees.add(new OtherEmployee("Barbara", 80004, 1400, "nurse"));

        student1.addSubjectGrade(0, 5);
        student1.addSubjectGrade(0, 4);
        student1.addSubjectGrade(0, 3);
        student1.addSubjectGrade(0, 5);
        student1.addSubjectGrade(0, 2);
    }


    /**
     * Method for getting from user numeric (int) input, handles mismatch of input type
     * @return user's input
     */
    private int getInput() {
        try {
            return new Scanner(System.in).nextInt();
        } catch (Exception e ) {
            System.out.print("Input not recognized, try again: ");
            return getInput();
        }
    }
}
