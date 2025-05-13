public class CinemaStaff extends Person{

    private String staffId;
    private String password;


    // Constructor with parameters
    public CinemaStaff(String name, String email, String staffId, String password){
        super(name, email);
        this.staffId = staffId;
        this.password = password;
    }

    // Getter and Setter methods
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}