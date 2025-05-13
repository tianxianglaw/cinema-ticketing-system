public class Customer extends Person {

    private int loyaltyPoint;

    // Constructor with parameters
    public Customer(String name, String email) {
        super(name, email); 
        this.loyaltyPoint = 0;
    }

    // Getter and Setter methods
    public int getLoyaltyPoint() {
        return loyaltyPoint;
    }

    public void setLoyaltyPoint(int loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
    }
    
    public void addLoyaltyPoints(int points) {
        this.loyaltyPoint += points;
    }
}
