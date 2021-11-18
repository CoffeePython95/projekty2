package school.management.system;

abstract class Person {
    /**
     * name of person
     */
    String name;
    /**
     * id assigned to person
     */
    int id;


    /**
     * Constructor for creating Person object with given name and id
     * @param name name of person
     * @param id id of person
     */
    Person(String name, int id) {
        this.name = name;
        this.id = id;
    }


    /**
     * Getter for name of person
     * @return name field of Person object
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for id of person
     * @return id field of Person object
     */
    public int getId() {
        return this.id;
    }
}
