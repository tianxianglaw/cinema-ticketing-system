public class Promotion {
    private Discount discount;
    private LoyaltyPoint loyaltyPoint;

    public Promotion(double originalPrice) {
        this.discount = new Discount(originalPrice);
        this.loyaltyPoint = new LoyaltyPoint(originalPrice);
    }

    public double getFinalPrice() {
        return discount.getDiscountedPrice();
    }

    public int getLoyaltyPoints() {
        return loyaltyPoint.calculatePoints();
    }
}