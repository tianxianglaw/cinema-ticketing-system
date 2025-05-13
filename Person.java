public class Person{

    private String name;
    private String email;

    

    // Constructor with parameters
    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}