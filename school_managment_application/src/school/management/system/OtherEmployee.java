package school.management.system;

public class OtherEmployee extends Worker{
    /**
     * occupation of otherEmployee
     */
    final private String occupation;


    /**
     * Constructor for creating OtherEmployee object with given name, id,  default salary value and chosen occupation
     * @param name name of otherEmployee
     * @param id id of otherEmployee
     * @param occupation designation of otherEmployee
     */
    public OtherEmployee(String name, int id, String occupation) {
        super(name, id);
        this.occupation = occupation;
    }

    /**
     * Constructor for creating OtherEmployee object with given name, id, salary value and occupation
     * @param name name of otherEmployee
     * @param id id of otherEmployee
     * @param occupation designation of otherEmployee
     */
    public OtherEmployee(String name, int id, int salary, String occupation) {
        super(name, id, salary);
        this.occupation = occupation;
    }


    /**
     * toString() method for OtherEmployee class
     * @return String summarising fields of this OtherEmployee object
     */
    @Override
    public String toString() {
        return String.format("\n\n%s\n\tId: %s\n\tName: %s\n\tSalary: $%s\n\tTotal Received: $%s\n",
                occupation.toUpperCase(), this.id, this.name, this.salary, this.salariesReceived);
    }
}
