//import required for UUID generation (random)
import java.util.UUID;

public class Payment {
    private String paymentMethod;
    private double amount;    
    private String transactionID;
    private String status;       

    //parameterised constructor
    public Payment(String paymentMethod, double amount) {
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.transactionID = generateTransactionID();
        this.status = "Pending"; //default payment status is PENDING
    }

    //generate unique transaction ID
    private String generateTransactionID() {
        String uuid = UUID.randomUUID().toString().replaceAll("[^0-9]", ""); //filter numeric characters
        return "T" + uuid.substring(0, 3); //extract the first 3 digits and prefix with "T"
    }

    //payment process method
    public void processPayment() {
        System.out.println("Processing payment using " + paymentMethod + "...");
        this.status = "Completed"; //mark status as completed for simplicity
    }

    //getter methods
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getStatus() {
        return status;
    }

    //setter methods
    public void setStatus(String status) {
        this.status = status;
    }

    //toString method
    @Override
    public String toString() {
        return "Payment Method: " + paymentMethod +
               "\nAmount Paid: RM" + String.format("%.2f", amount) +
               "\nTransaction ID: " + transactionID +
               "\nStatus: " + status;
    }
}