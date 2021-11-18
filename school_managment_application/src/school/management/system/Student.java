package school.management.system;

import java.util.ArrayList;

public class Student extends Person{
    /**
     * student's grade
     */
    private int grade;
    /**
     * how much fees student paid so far
     */
    private int feesPaid;
    /**
     * fees students needs to pay
     */
    final private static int feesTotal = 10000;
    /**
     * list of students grades
     */
    final private ArrayList<SubjectGrades> subjects;


    /**
     * Constructor for creating Student object with given name, id and default (1st) grade. Takes default ($2500) fee
     * @param name name of student
     * @param id id of student
     */
    public Student(String name, int id) {
        super(name, id);
        this.grade = 1;

        this.subjects = new ArrayList<>();
        payFees(2500);
        createSubjects();
    }

    /**
     * Constructor for creating Student object with given name, id and grade. Takes default ($2500) fee
     * @param name name of student
     * @param id id of student
     * @param grade grade of student
     */
    public Student(String name, int id, int grade) {
        super(name, id);
        this.grade = grade;

        this.subjects = new ArrayList<>();
        payFees(2500);
        createSubjects();
    }


    /**
     * Method for initializing subjects list with default subjects
     */
    private void createSubjects() {
        new SubjectGrades("Math", this);
        new SubjectGrades("Physics", this);
        new SubjectGrades("English", this);
        new SubjectGrades("PE", this);
        new SubjectGrades("Biology", this);
        new SubjectGrades("Geography", this);
        new SubjectGrades("History", this);
    }

    /**
     * Method for printing out names of subjects in subjects list
     */
    public void printSubjects() {
        for (int i = 0; i < this.subjects.size(); i++) {
            System.out.printf("\t%d. %s\n", (i + 1), this.subjects.get(i).getNameOfSubject());
        }
    }

    /**
     * Method for checking how many subjects are stored in subjects list
     * @return size of subjects list
     */
    public int numSubjects() {
        return subjects.size();
    }

    /**
     * Method for adding subject to students subjects list
     * @param subject subject to add to list
     */
    public void addSubject(SubjectGrades subject) {
        this.subjects.add(subject);
    }

    /**
     * Method for adding grades to list of grades of student
     * @param index which subjects grade it is
     * @param grade what grade it is
     */
    public void addSubjectGrade(int index, int grade) {
        if(grade >= 1 && grade <= 6) {
            subjects.get(index).addGrade(grade);
        }
    }

    /**
     * Method for printing out subjects name with its grades
     * @param subjectIndex index of subject in subjects list of student
     */
    public void printSubjectGrades(int subjectIndex) {
        System.out.println(this.subjects.get(subjectIndex).toString());
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public void payFees(int fee) {
        if (isAmountPositive(fee)) {
            this.feesPaid += fee;
            School.updateMoneyEarned(fee);
        } else {
            System.out.println("Incorrect fee");
        }
        if (areFeesOverPaid()) {
            int excess = this.feesPaid - this.getFeesTotal();
            this.feesPaid-=excess;
            School.updateMoneyEarned(-excess);
            System.out.println("Fees already paid, excess money ($" + excess + ") returned.");
        }
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public int getFeesTotal() {
        return feesTotal;
    }

    @Override
    public String toString() {
        return "\n\nSTUDENT\n\tId: " + this.id + "\n\tName: " + this.name + "\n\tGrade: " + grade + "\n\tFees paid/fees total: " + feesPaid + "/" + feesTotal;
    }

    public boolean isAmountPositive(int amount) {
        return amount > 0;
    }

    public boolean areFeesOverPaid() {
        return this.feesPaid > 10000;
    }

}
