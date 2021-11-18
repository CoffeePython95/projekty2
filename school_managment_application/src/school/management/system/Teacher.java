package school.management.system;

public class Teacher extends Worker{
    /**
     * Constructor for creating Teacher object with given name, id and default salary value
     * @param name name of teacher
     * @param id id of teacher
     */
    public Teacher(String name, int id) {
        super(name, id);
        this.salary = 2500;
    }

    /**
     * Constructor for creating Teacher object with given name, id and salary value
     * @param name name of teacher
     * @param id id of teacher
     * @param salary salary of teacher
     */
    public Teacher(String name, int id, int salary) {
        super(name, id, salary);
    }


    /**
     * toString() method for Teacher class
     * @return String summarising fields of this Teacher object
     */
    @Override
    public String toString() {
        return String.format("\n\nTEACHER\n\tID: %s\n\tName: %s\n\tSalary: $%s\n\tTotal Received: $%s\n",
                this.id, this.name, this.salary, this.salariesReceived);
    }
}
