public class OnlineBanking extends Payment {
    private int accNum;
    private String bankCompany;
    private int pin;

    //parameterized constructor
    public OnlineBanking(String paymentMethod, double amount, int accNum, String bankCompany, int pin) {
        super(paymentMethod, amount);
        this.accNum = accNum;
        this.bankCompany = bankCompany;
        this.pin = pin;
    }

    //getter methods
    public int getAccNum() {
        return accNum;
    }

    public String getBankCompany() {
        return bankCompany;
    }

    public int getPin() {
        return pin;
    }

    //setter methods
    public void setAccNum(int accNum) {
        this.accNum = accNum;
    }

    public void setBankCompany(String bankCompany) {
        this.bankCompany = bankCompany;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    //menu for users to choose their preferred bank
    public void BankList() {
        System.out.println("=========================");
        System.out.println("      Available Banks       ");
        System.out.println("=========================");
        System.out.println("1. Public Bank");
        System.out.println("2. Maybank");
        System.out.println("3. CIMB Bank");
        System.out.println("4. Hong Leong Bank");
        System.out.println("=========================");
    }

    //preferred bank choose by customer to make their payment
    public void PublicBank() {
        System.out.println("Selected Bank Company: Public Bank");
    }

    public void Maybank() {
        System.out.println("Selected Bank Company: Maybank");
    }

    public void CIMBBank() {
        System.out.println("Selected Bank Company: CIMB Bank");
    }

    public void HongLeongBank() {
        System.out.println("Selected Bank Company: Hong Leong Bank");
    }

    //payment process method
    @Override
    public void processPayment() {
        System.out.println("Processing online banking payment...");
        System.out.println("Bank Company: " + bankCompany);
        System.out.println("Account Number: " + accNum);
        System.out.println("Verifying PIN: " + pin);
        super.setStatus("Completed");
        System.out.println("Payment Successful. Transaction ID: " + super.getTransactionID());
    }

    //toString method
    @Override
    public String toString() {
        return super.toString() +
               "\nAccount Number: " + accNum +
               "\nBank Company: " + bankCompany;
    }
}