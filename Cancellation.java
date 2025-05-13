import java.util.UUID;

public class Cancellation {
    private String cancellationID;
    private MovieTicket ticket;
    private boolean cancelled;

    //parameterised constructor
    public Cancellation(MovieTicket ticket){
        this.cancellationID = generateCancellationID();
        this.ticket = ticket;
        this.cancelled = false;
    }

    public String getCancellationID(){
        return cancellationID;
    }

    public MovieTicket getTicket(){
        return ticket;
    }

    public boolean getCancelled(){
        return cancelled;
    }

    public void setCancelled(boolean cancelled){
        this.cancelled = cancelled;
    }
    //generate unique cancellationID
    private String generateCancellationID(){
        String uuid = UUID.randomUUID().toString().replaceAll("[^0-9]",""); //filter numeric characters
        return "C" + uuid.substring(0,5); //extract the first 5 digits and prefic with "C"
    }

    //to perform ticket cancellation
    public boolean cancelTicket(){
        //check if the ticket is already cancelled
        if(this.getCancelled()){
            System.out.println("This ticket has already been cancelled!");
            return false; //exit if the ticket is already cancelled
        }

        //mark the ticket as cancelled
        this.setCancelled(true); //update the ticket status
        System.out.println("Ticket has been cancelled successfully!");
        System.out.println("Cancellation ID: " + this.cancellationID);
        ticket.printTicket();

        processRefund();

        return true; //indicate successful cancellation
    }

    public void processRefund() {
        System.out.println("Processing refund...");
    
        //ensure ticket object is not null before proceeding
        if (ticket == null) {
            System.out.println("Error: Ticket information not found.");
            return;
        }
    
        //retrieve total price for refund
        double refundAmount = ticket.getTotalPrice();
        System.out.println("Refund amount: RM" + String.format("%.2f", refundAmount));
    
        //ensure customer object is not null before proceeding
        if (ticket.getCustomer() == null) {
            System.out.println("Error: Customer details missing.");
            return ;
        }
    
        System.out.println("Refund successfully send to the customer.");

        
    }
}
