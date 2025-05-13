public class DebitCard extends Payment {
    private String debitCardNum;
    private int cvv;
    private int pin;

    //parameterised constructor
    public DebitCard(String paymentMethod, double amount, String debitCardNum, int cvv, int pin) {
        super(paymentMethod, amount);
        this.debitCardNum = debitCardNum;
        this.cvv = cvv;
        this.pin = pin;
    }

    //getter methods
    public String getDebitCardNum() {
        return debitCardNum;
    }

    public int getCvv() {
        return cvv;
    }

    public int getPin() {
        return pin;
    }

    //setter methods
    public void setDebitCardNum(String debitCardNum) {
        this.debitCardNum = debitCardNum;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    //hide debit card number for security purposes
    private String hideDebitCardNum(String number) {
        String hiddenCardNum = "";
        int length = number.length();

        for (int i = 0; i < length - 4; i++) {
            hiddenCardNum += "*";
        }

        hiddenCardNum += number.substring(length - 4); //only show the last 4 digits of the debit card number
        return hiddenCardNum;
    }

    //payment process method
    @Override
    public void processPayment() {
        System.out.println("Processing debit card payment...");
        System.out.println("Debit Card Number: " + hideDebitCardNum(debitCardNum));
        System.out.println("Verifying CVV: " + cvv);
        System.out.println("Verifying PIN: " + pin);
        super.setStatus("Completed"); 
        System.out.println("Payment Successful. Transaction ID: " + super.getTransactionID());
    }

    //toString method
    @Override
    public String toString() {
        return super.toString() +
               "\nDebit Card Number: " + hideDebitCardNum(debitCardNum);
    }
}