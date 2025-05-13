public class MovieTicket {
    private Customer customer;
    private Movie movie;
    private Showtime showtime;
    private int ticketCount;
    private String[] selectedSeats;
    private double totalPrice;
    private int adultTickets;
    private int childrenTickets;

    public MovieTicket(Customer customer, Movie movie, Showtime showtime, int adultTickets, int childrenTickets, String[] selectedSeats, double totalPrice) {
        this.customer = customer;
        this.movie = movie;
        this.showtime = showtime;
        this.ticketCount = adultTickets + childrenTickets;
        this.selectedSeats = selectedSeats;
        this.totalPrice = totalPrice;
        this.adultTickets = adultTickets;
        this.childrenTickets = childrenTickets;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Movie getMovie() {
        return movie;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public String[] getSelectedSeats() {
        return selectedSeats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getAdultTickets() {
        return adultTickets;
    }

    public int getChildrenTickets() {
        return childrenTickets;
    }

    public void printTicket() {
        System.out.println("\nMovie Ticket:");
        System.out.println("Movie: " + movie.getTitle());
        System.out.println("Showtime: " + showtime.getDetails());
        System.out.println("Seats: " + String.join(", ", selectedSeats));
        System.out.println("Adult Tickets: " + adultTickets);
        System.out.println("Children Tickets: " + childrenTickets);
        System.out.println("Total Price: RM" + totalPrice);
        System.out.println("Thank you for your purchase!");
    }
}