public class LoyaltyPoint {
    private double price; //declare price 

    public LoyaltyPoint(double price) { //loyalty point constructor 
        this.price = price;
    }

    public double getPrice() { //price getter 
        return price;
    }

    public void setPrice(double price) { //price setter
        this.price = price;
    }

    public int calculatePoints() { //calculate loyalty point
        return (int) (price / 5); //1 point for every RM5 spent and convert to integer
    }
}