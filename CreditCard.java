public class CreditCard extends Payment {
    private String creditCardNum;
    private int cvv; 
    private int pin;

    //parameterised constructor
    public CreditCard(String paymentMethod, double amount, String creditCardNum, int cvv, int pin) {
        super(paymentMethod, amount);
        this.creditCardNum = creditCardNum;
        this.cvv = cvv;
        this.pin = pin;
    }

    //getter methods
    public String getCreditCardNum() {
        return creditCardNum;
    }

    public int getCvv() {
        return cvv;
    }

    public int getPin() {
        return pin;
    }

    //setter methods
    public void setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    //hide credit card number for security purposes
    private String hideCreditCardNum(String number) {
        String hiddenCardNum = "";
        int length = number.length();

        for (int i = 0; i < length - 4; i++) {
            hiddenCardNum += "*";
        }

        hiddenCardNum += number.substring(length - 4); //only show the last 4 digits of the credit card number
        return hiddenCardNum;
    }

    //payment process method
    @Override
    public void processPayment() {
        System.out.println("Processing credit card payment...");
        System.out.println("Credit Card Number: " + hideCreditCardNum(creditCardNum));
        System.out.println("Verifying CVV: " + cvv);
        System.out.println("Verifying PIN: " + pin);
        super.setStatus("Completed");
        System.out.println("Payment Successful. Transaction ID: " + super.getTransactionID());
    }

    //toString method
    @Override
    public String toString() {
        return super.toString() +
               "\nCredit Card Number: " + hideCreditCardNum(creditCardNum);
    }
}