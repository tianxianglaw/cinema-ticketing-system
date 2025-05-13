public class Discount {
    private double originalPrice; //declare original price 

    public Discount(double originalPrice) { //discount constructor  
        this.originalPrice = originalPrice;
    }

    public double getOriginalPrice() { //original price getter
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) { // original prive setter 
        this.originalPrice = originalPrice;
    }

    public double getDiscountedPrice() { //calculate discount price 
        if (originalPrice > 60) {
            return originalPrice * 0.85; //get the price after 15% discount
        }
        return originalPrice; //no discount while total price is RM60 or below
    }
}