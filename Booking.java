
import java.util.Scanner;
import java.util.List;

public class Booking {
    // Data attributes
    private Customer customer;      
    private Movie movie;
    private Showtime showtime;
    private int adultTickets;
    private int childrenTickets;
    private double totalPrice;
    private String[] selectedSeats; 
    private int loyaltyPoints;

    private Cancellation cancellation;
    private String bookingStatus; // used to track booking status

    // Composition attributes
    private Promotion promotion;
    private Payment payment;
    private MovieTicket movieTicket;

    

    // Constructor
    public Booking(Customer customer, Movie movie, Showtime showtime, int adultTickets, int childrenTickets, String[] selectedSeats) {
        this.customer = customer; 
        this.movie = movie;
        this.showtime = showtime;
        this.adultTickets = adultTickets;
        this.childrenTickets = childrenTickets;
        this.selectedSeats = selectedSeats;
        this.bookingStatus = "Confirmed";

        calculateTotalPrice();
        applyPromotion();
        createMovieTicket();
    }

    public Customer getCustomer(){
        return customer;
    }

    public Movie getMovie(){
        return movie;
    }

    public Showtime getShowtime(){
        return showtime;
    }

    public String[] getSelectedSeats(){
        return selectedSeats;
    }

    public Cancellation getCancellation(){
        return cancellation;
    }

    public void setCancellation(Cancellation cancellation){
        this.cancellation = cancellation;
    }

    public String getBookingStatus(){
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus){
        this.bookingStatus = bookingStatus;
    }


    // Calculate total price
    public void calculateTotalPrice() {
        double adultPrice = 30.0; 
        double childPrice = 20.0; 
        totalPrice = (adultTickets * adultPrice) + (childrenTickets * childPrice);
    }

    // Apply promotion
    private void applyPromotion() {
        promotion = new Promotion(totalPrice); 
        totalPrice = promotion.getFinalPrice(); 
        loyaltyPoints = promotion.getLoyaltyPoints();
        customer.addLoyaltyPoints(loyaltyPoints);
    }

    // Instantiate MovieTicket
    private void createMovieTicket() {
        movieTicket = new MovieTicket(customer, movie, showtime, adultTickets, childrenTickets, selectedSeats, totalPrice); 
    }

    // Display booking details
    public void displayBookingDetails() {
        System.out.println("\nBooking Details:");
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Movie: " + movie.getTitle());
        System.out.println("Showtime: " + showtime.getDetails());
        System.out.println("Seats: " + String.join(", ", selectedSeats));
        System.out.println("Adult Tickets: " + adultTickets);
        System.out.println("Children Tickets: " + childrenTickets);
        System.out.println("Total Price (after promotion): RM" + totalPrice);
        System.out.println("Loyalty Points Earned: " + loyaltyPoints);

        if (cancellation != null){
            System.out.println("Cancellation ID: "+cancellation.getCancellationID());
            System.out.print("Cancellation Status: "+ (cancellation.getCancelled()?"Cancelled":"Not Cancelled"));

        }else{
            System.out.println("Cancellation ID: N/A");
        }
        System.out.println("Booking Status: "+ bookingStatus);
    }

    // Handle payment process
    public void makePayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nChoose Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        System.out.println("3. E-Wallet");
        System.out.println("4. Online Banking");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        // Instantiate appropriate Payment object
        switch (choice) {
            case 1 -> {
                System.out.print("Enter Credit Card Number: ");
                String cardNumber = scanner.next();
                System.out.print("Enter CVV: ");
                int cvv = scanner.nextInt();
                System.out.print("Enter PIN: ");
                int pin = scanner.nextInt();
                payment = new CreditCard("Credit Card", totalPrice, cardNumber, cvv, pin);
            }
            case 2 -> {
                System.out.print("Enter Debit Card Number: ");
                String cardNumber = scanner.next();
                System.out.print("Enter CVV: ");
                int cvv = scanner.nextInt();
                System.out.print("Enter PIN: ");
                int pin = scanner.nextInt();
                payment = new DebitCard("Debit Card", totalPrice, cardNumber, cvv, pin);
            }
            case 3 -> {
                System.out.print("Enter Wallet ID: ");
                String walletId = scanner.next();
                System.out.print("Enter PIN: ");
                int pin = scanner.nextInt();
                payment = new EWallet("E-Wallet", totalPrice, walletId, pin);
            }
            case 4 -> {
                System.out.print("Enter Account Number: ");
                int accountNumber = scanner.nextInt();
                System.out.print("Enter Bank Name: ");
                String bankName = scanner.next();
                System.out.print("Enter PIN: ");
                int pin = scanner.nextInt();
                payment = new OnlineBanking("Online Banking", totalPrice, accountNumber, bankName, pin);
            }
            default -> {
                System.out.println("Invalid choice. Payment aborted.");
                return;
            }
        }

        payment.processPayment();
        printTicket();
    }

    // Print ticket
    private void printTicket() {
        movieTicket.printTicket();
    }

    // Start booking process
    public static void startBooking(Movie[] movieList, Customer customer, List<Booking>bookingHistory) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Movie Selection
        System.out.println("\nAvailable Movies:");
        for (int i = 0; i < movieList.length; i++) {
            System.out.println((i + 1) + ". " + movieList[i].getTitle());
        }
        System.out.print("\nChoose a movie: ");
        int movieChoice = scanner.nextInt();
        if (movieChoice < 1 || movieChoice > movieList.length) {
            System.out.println("Invalid choice. Returning to main menu...");
            return;
        }
        Movie selectedMovie = movieList[movieChoice - 1];

        // Step 2: Showtime Selection
        System.out.println("\nAvailable Showtimes:");
        Showtime[] showtimes = selectedMovie.getShowtimes();
        for (int i = 0; i < showtimes.length; i++) {
            System.out.println((i + 1) + ". " + showtimes[i].getDetails());
        }
        System.out.print("\nChoose a showtime: ");
        int showtimeChoice = scanner.nextInt();
        if (showtimeChoice < 1 || showtimeChoice > showtimes.length) {
            System.out.println("Invalid choice. Returning to main menu...");
            return;
        }
        Showtime selectedShowtime = showtimes[showtimeChoice - 1];

        scanner.nextLine();

        // Step 3: Seat Selection
        boolean seatsSelected = false;
        String[] seatArray = new String[0]; // Initialize as empty array

        while (!seatsSelected) {
            System.out.println("\nAvailable Seats for " + selectedShowtime.getDetails() + ":");
            selectedShowtime.displaySeats(); // Call SeatArrangement to show seats
            System.out.println("Enter seat numbers (e.g., A1, B2) separated by commas:");
            String seatInput = scanner.nextLine();
            seatArray = seatInput.split(",");
            boolean success = true;
            for (String seat : seatArray) {
                String trimmedSeat = seat.trim().toUpperCase();
                if (trimmedSeat.length() < 2 || !Character.isLetter(trimmedSeat.charAt(0)) || !trimmedSeat.substring(1).matches("\\d+")) {
                    System.out.println("Invalid seat format: " + seat + ". Please use format like A1.");
                    success = false;
                    break;
                }
                int row = trimmedSeat.charAt(0) - 'A';
                int col = Integer.parseInt(trimmedSeat.substring(1)) - 1;

                // Directly check if the row and col are within bounds BEFORE attempting to book
                if (row < 0 || row >= selectedShowtime.getSeatArrangement().getRows() || col < 0 || col >= selectedShowtime.getSeatArrangement().getColumns()) {
                    System.out.println("Seat " + trimmedSeat + " is invalid.");
                    success = false;
                    break;
                } else if (!selectedShowtime.bookSeat(row, col)) {
                    System.out.println("Seat " + trimmedSeat + " is unavailable.");
                    success = false;
                    break;
                }
            }

            if (success) {
                seatsSelected = true;
            } else {
                System.out.println("Failed to book all seats. Please try again.");
                // Release any seats that might have been temporarily booked in this attempt
                for (String seat : seatArray) {
                    String trimmedSeat = seat.trim().toUpperCase();
                    if (trimmedSeat.length() >= 2 && Character.isLetter(trimmedSeat.charAt(0)) && trimmedSeat.substring(1).matches("\\d+")) {
                        int r = trimmedSeat.charAt(0) - 'A';
                        int c = Integer.parseInt(trimmedSeat.substring(1)) - 1;
                        if (r >= 0 && r < selectedShowtime.getSeatArrangement().getRows() && c >= 0 && c < selectedShowtime.getSeatArrangement().getColumns() && selectedShowtime.getSeatArrangement().getSeats()[r][c].equals("B")) {
                            selectedShowtime.cancelSeatBooking(r, c);
                        }
                    }
                }
            }
        }

        // Step 4: Ticket Selection
        System.out.print("Enter the number of adult tickets: ");
        int adultTickets = scanner.nextInt();
        System.out.print("Enter the number of children tickets: ");
        int childrenTickets = scanner.nextInt();

        // Step 5: Create Booking Instance
        Booking booking = new Booking(customer, selectedMovie, selectedShowtime, adultTickets, childrenTickets, seatArray);

        // Step 6: Display Booking Details and Generate Ticket
        booking.displayBookingDetails();
        booking.makePayment();

        bookingHistory.add(booking);
    }

    public MovieTicket getMovieTicket(){
        return movieTicket;
    }

}
