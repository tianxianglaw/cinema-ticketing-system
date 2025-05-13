public class EWallet extends Payment {
    private String touchNGoNo;
    private int pin;

    //parameterised constructor
    public EWallet(String paymentMethod, double amount, String touchNGoNo, int pin) {
        super(paymentMethod, amount);
        this.touchNGoNo = touchNGoNo;
        this.pin = pin;
    }

    //getter methods
    public String getTouchNGoNo() {
        return touchNGoNo;
    }

    public int getPin() {
        return pin;
    }

    //setter methods
    public void setTouchNGoNo(String touchNGoNo) {
        this.touchNGoNo = touchNGoNo;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    //hide Touch 'n Go number for security purposes
    private String hideTouchNGoNo(String number) {
        String hiddenNo = "";
        int length = number.length();

        for (int i = 0; i < length - 4; i++) {
            hiddenNo += "*";
        }

        hiddenNo += number.substring(length - 4); //only show the last 4 digits of the TNG number
        return hiddenNo;
    }

    //payment process method
    @Override
    public void processPayment() {
        System.out.println("Processing e-wallet payment...");
        System.out.println("Touch 'n Go Number: " + hideTouchNGoNo(touchNGoNo));
        System.out.println("Verifying PIN: " + pin);
        super.setStatus("Completed");
        System.out.println("Payment Successful. Transaction ID: " + super.getTransactionID());
    }

    //toString method
    @Override
    public String toString() {
        return super.toString() +
               "\nTouch 'n Go Number: " + hideTouchNGoNo(touchNGoNo);
    }
}