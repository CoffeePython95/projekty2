
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private int grade;
    private String email;

    public Student() {

    }


    // Setters & Getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if(gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("male")) {
            this.gender = "Male";
        } else if(gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("female")) {
            this.gender = "Female";
        } else {
            System.out.print("Input doesn't meet criteria. Try again: ");
            setGender(new Scanner(System.in).next());
        }
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * toString() method of Student
     * @return String with all Students fields
     */
    @Override
    public String toString() {
            String output =  "\nStudent " + firstName + " " + lastName +
                    "\n{" +
                    "id : " + id +
                    ", gender : '" + gender + '\'' +
                    ", date_of_birth : " + dateOfBirth +
                    ", grade : " + grade +
                    ", email : '";
            output += Objects.requireNonNullElse(email, "not given") + '\'' +
                '}';
            return output;
    }
}
