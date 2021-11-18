package school.management.system;

import java.util.ArrayList;

public class SubjectGrades {
    /**
     * name of the subject
     */
    final private String nameOfSubject;
    /**
     * grades received for subject
     */
    final private ArrayList<Integer> grades;
    /**
     * id of students to whom those grades belong to
     */
    final private int studentsID;


    /**
     * Constructor for creating Subject object with given name and empty grades array.
     * Adds itself to student's subjects list
     * @param nameOfSubject name of this particular school subject
     * @param student Student object linked to this subject grades
     */
    SubjectGrades(String nameOfSubject, Student student) {
        this.nameOfSubject = nameOfSubject;
        this.grades = new ArrayList<>();
        this.studentsID = student.getId();

        student.addSubject(this);
    }


    /**
     * Getter for name of subject
     * @return name of subject
     */
    public String getNameOfSubject() {
        return this.nameOfSubject;
    }


    /**
     * Method for adding new grade to list of grades
     * @param grade grade to add to list of grades
     */
    public void addGrade(int grade) {
        this.grades.add(grade);
    }

    /**
     * Method for creating String containing all grades for selected subject
     * @return String containing all grades for selected subject
     */
    private String printGrades() {
        if(grades.size() == 0) {
            return "no grades received yet";
        }
        StringBuilder sb = new StringBuilder(grades.size());
        for (int grade: grades) {
            sb.append(grade).append(", ");
        }
        return sb.toString();
    }


    /**
     * toString() method for SubjectGrades
     * @return String with name of subject and its grades
     */
    @Override
    public String toString() {
        return nameOfSubject.toUpperCase() + ": " + printGrades();
    }
}
