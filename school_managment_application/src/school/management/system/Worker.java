package school.management.system;

abstract class Worker extends Person{
    /**
     * salary of worker
     */
    int salary;
    /**
     * salary received by worker so far
     */
    int salariesReceived;


    /**
     * Constructor for creating Worker object with given name, id and default salary value
     * @param name name of worker
     * @param id id of worker
     */
    public Worker(String name, int id) {
        super(name, id);
        this.salary = 1500;
    }

    /**
     * Constructor for creating Worker object with given name, id and salary value
     * @param name name of worker
     * @param id id of worker
     * @param salary salary of worker
     */
    public Worker(String name, int id, int salary) {
        super(name, id);
        this.salary = salary;
    }


    /**
     * Setter for salary of worker
     * @param salary new salary value
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * Getter for salary of worker
     * @return salary field of Worker object
     */
    public int getSalary() {
        return this.salary;
    }


    /**
     * Method for increasing value of salariesReceived field by salary field value, also updates moneySpent field value of School class
     */
    public void receiveSalary() {
        this.salariesReceived += salary;
        School.updateMoneySpent(salary);
    }
}
