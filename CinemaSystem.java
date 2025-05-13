
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class CinemaSystem {
    // Make movieList a class-level variable for shared access
    static final Movie[] movieList = new Movie[3]; // Declare and initialize
    static Customer loggedInCustomer = null; // keep track of loggedin customer
    static List<Booking> bookingHistory = new ArrayList<>();

    public static void main(String[] args) {
        // Create Cinema staff
        CinemaStaff admin = new CinemaStaff("Johnny", "johnny@gmail.com", "admin123", "admin@123");

        // Creating movie objects and showtimes
        Movie movie1 = new Movie("Inception", "Sci-Fi", "Christopher Nolan", "Leonardo DiCaprio, Joseph Gordon-Levitt", "English", "2h 28m", 8.8);
        movie1.addShowtime("10:00 AM", "20-4-2025", 1, 5, 5);
        movie1.addShowtime("2:00 PM", "21-4-2025", 2, 5, 5);

        Movie movie2 = new Movie("The Dark Knight", "Action", "Christopher Nolan", "Christian Bale, Heath Ledger", "English", "2h 32m", 9.0);
        movie2.addShowtime("11:00 AM", "22-4-2025", 1, 5, 5);
        movie2.addShowtime("3:00 PM", "23-4-2025", 3, 5, 5);

        Movie movie3 = new Movie("Interstellar", "Sci-Fi", "Christopher Nolan", "Matthew McConaughey, Anne Hathaway", "English", "2h 49m", 8.6);
        movie3.addShowtime("12:00 PM", "24-4-2025", 1, 5, 5);
        movie3.addShowtime("4:00 PM", "25-4-2025", 2, 5, 5);

        // Adding movie objects to the array
        movieList[0] = movie1;
        movieList[1] = movie2;
        movieList[2] = movie3;

        boolean contd = true; // Flag to control the loop

        do {
            try {
                int choice = MainMenu(); // Display main menu and get user choice
                switch (choice) {
                    case 1:
                        customerPage(); // Access the customer page
                        break;
                    case 2:
                        staffPage(admin); // Access the staff page
                        break;
                    case 3:
                        System.out.println("Exiting the system. Goodbye!");
                        contd = false; // Exit the loop
                        break;
                    default:
                        System.out.println("Invalid input. Try Again.");
                }
            } catch (Exception e) {
                System.out.println("Please enter proper input.");
            }
        } while (contd);
    }

    // Method to show Main Menu
    public static int MainMenu() {
        System.out.println("");
        System.out.println("=========================================");
        System.out.println("|   Welcome to the Siok Cinema System!  |");
        System.out.println("|=======================================|");
        System.out.println("| 1. Customer                           |");
        System.out.println("| 2. Cinema Staff                       |");
        System.out.println("| 3. Exit                               |");
        System.out.println("=========================================");

        Scanner s1 = new Scanner(System.in);
        System.out.print("Please enter your choice: ");
        return s1.nextInt();
    }

    public static void customerPage() {
        Scanner s1 = new Scanner(System.in);
        String name;
        String email;
    
        System.out.println("\n");
        System.out.println("=========================");
        System.out.println("|  Customer Login Page  |");
        System.out.println("=========================");
    
        // Collect name
        do {
            System.out.print("Please enter your name: ");
            name = s1.nextLine();
    
            if (name.isEmpty()) {
                System.out.println("Please fill in the name.\n");
            } else if (name.matches("\\d+")) {
                System.out.println("Name cannot contain digits.\n");
            }
        } while (name.isEmpty() || name.matches("\\d+"));
    
        // Collect email
        do {
            System.out.print("Please enter your email: ");
            email = s1.nextLine();
    
            if ((email.isEmpty())) {
                System.out.println("Please fill in email.\n");
            } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                System.out.println("Please enter a valid email format.\n");
            }
        } while ((email.isEmpty()) || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"));
    
        System.out.println("\n\nLogin successful! Welcome, " + name + "!");
        
        // Create a Customer object after login
        if (loggedInCustomer == null || !loggedInCustomer.getEmail().equals(email)){
            loggedInCustomer = new Customer(name,email);
        }
            
        int option;
    
        do {
            System.out.println("\n");
            System.out.println("========================================");
            System.out.println("|            Customer Page             |");
            System.out.println("========================================");
            System.out.println("| 1. View Movie List                   |");
            System.out.println("| 2. Book Ticket                       |");
            System.out.println("| 3. Check Loyalty Points              |");
            System.out.println("| 4. Back to Main Menu                 |");
            System.out.println("========================================");
    
            System.out.print("Please enter your option: ");
            option = s1.nextInt();
            s1.nextLine(); // Clear buffer
    
            switch (option) {
                case 1:
                    System.out.println("\nAvailable Movie List:\n");
                    for (Movie movie : movieList) {
                        System.out.println(movie.displayMovieInfo()); // Show all movies
                    }
                    break;
                case 2:
                    System.out.println("\nRedirecting to Booking Page...");
                    // Pass the logged-in customer to the Booking process
                    Booking.startBooking(movieList, loggedInCustomer, bookingHistory);
                    break;
                case 3:

                    if (loggedInCustomer != null){
                        System.out.println("\nYour loyalty points: "+ loggedInCustomer.getLoyaltyPoint());
                        if(loggedInCustomer.getLoyaltyPoint() == 0){
                            System.out.println("You can book tickets to earn more points.");
                    }
                    }else{
                        System.out.println("Error: No customer logged in.");
                    }
                break;  
                case 4:
                    break;
                default:
                    System.out.println("Please enter a valid integer.");
            }
        } while (option != 4);
    }


    public static void staffPage(CinemaStaff admin) {
        Scanner s1 = new Scanner(System.in);
        String staffId;
        String staffPassword;

        System.out.println("\n");
        System.out.println("=========================");
        System.out.println("|   Staff Login Page    |");
        System.out.println("=========================");

        //collect staff id
        do{
            System.out.print("Please enter your Staff ID: ");
            staffId = s1.nextLine();

            if (staffId.isEmpty()){
                System.out.println("Please fill in the Staff ID.\n");
            }else if (staffId.equals(admin.getStaffId())){
                do{
                    System.out.print("\nPlease enter your password: ");
                    staffPassword = s1.nextLine();
                    if (staffPassword.isEmpty()){
                        System.out.print("Please fill in the password2");
                    }else if(staffPassword.equals(admin.getPassword())){
                        System.out.print("Login Succussful!");
                        break;
                    }else{
                        System.out.println("Incorrect password. Please try again.\n");
                    }

                }while(true);
                break;
                
            }else{
                System.out.println("Invalid staff ID. Please try again.\n");
            }
        }while(true);

        int option;

        do{
            System.out.println("\n");
            System.out.println("========================================");
            System.out.println("|              Staff Page              |");
            System.out.println("========================================");
            System.out.println("| 1. Manage Schedule                   |");
            System.out.println("| 2. Reporting System                  |");
            System.out.println("| 3. Cancellation                      |");
            System.out.println("| 4. Back To Main Menu                 |");
            System.out.println("========================================");

            System.out.print("Please enter your option: ");
            option = s1.nextInt();
            s1.nextLine(); // clear buffer

            switch(option){
                case 1:
                    manageSchedule();
                    break;
                case 2:
                    Reporting.ReportMenu(admin);
                    break;
                case 3:
                    handleCancellation();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }while (option!=4);
    }

    public static void manageSchedule(){
        Scanner s1 = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("=========================");
        System.out.println("| Manage Movie Schedule |");
        System.out.println(" ========================");

        //Display the list of movie
        System.out.println(":\nAvailable Movies: ");
        for (int i = 0; i< movieList.length; i++){
            if (movieList[i] != null){
                System.out.println((i+1)+ ". "+ movieList[i].getTitle());
            }
        }

        System.out.print("Enter the number of the movie you want to add showtime (or 0 to go back): ");
        int movieChoice = s1.nextInt();
        s1.nextLine();

        if(movieChoice > 0 && movieChoice <= movieList.length && movieList[movieChoice-1] != null){
            Movie selectedMovie = movieList[movieChoice -1];

            System.out.println("\nAdd showtime for: "+ selectedMovie.getTitle());

            System.out.print("Enter the showtime(eg: 10:50 AM): ");
            String time = s1.nextLine();

            System.out.print("Enter the date(eg: 03-03-2025): ");
            String date = s1.nextLine();

            System.out.print("Enter the hall number: ");
            int hallNo = s1.nextInt();

            System.out.print("Enter number of rows in the hall: ");
            int rows = s1.nextInt();

            System.out.print("Enter number of columns in the hall: ");
            int columns = s1.nextInt();
            s1.nextLine(); //newline

            selectedMovie.addShowtime(time, date, hallNo, rows, columns);
            System.out.println("\nShowtime added successfull for "+ selectedMovie.getTitle());
        }else if (movieChoice != 0){
            System.out.println("Invalid movie choice.");
        }else{
            System.out.println("Returned to Staff Menu");
        }
    
    }

    public static void handleCancellation(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n");
        System.out.println("==============================");
        System.out.println("|      Cancellation Page     |");
        System.out.println("==============================");

        if (bookingHistory.isEmpty()){
            System.out.println("No Bookings available for cancellation.");
            return;
        }

        System.out.println("\nCurrent Bookings: ");
        for (int i = 0; i< bookingHistory.size();i++){
            System.out.println((i+1)+ ". Booking for " + bookingHistory.get(i).getCustomer().getName()+
                " - "+ bookingHistory.get(i).getMovie().getTitle() +
                " - " + bookingHistory.get(i).getShowtime().getDetails() +
                " (Cancellation ID: " + (bookingHistory.get(i).getCancellation() != null ?
                bookingHistory.get(i).getCancellation().getCancellationID() : "N/A") + ")");


        }

        System.out.print("Enter the number of the booking you want to cancel (or 0 to go back): ");
        int bookingChoice = scanner.nextInt();
        scanner.nextLine();

        if (bookingChoice > 0 && bookingChoice <= bookingHistory.size()) {
            Booking bookingToCancel = bookingHistory.get(bookingChoice - 1);
            MovieTicket ticketToCancel = bookingToCancel.getMovieTicket();

            if (ticketToCancel == null){
                System.out.println("Error: Movie ticket is not found");
                return;
            }

            System.out.println("\nConfirm cancellation for: ");
            bookingToCancel.displayBookingDetails();

            System.out.print("Do you sure u want to cancel this booking? (yes/no): ");
            String confirmation = scanner.nextLine().toLowerCase();

            if (confirmation.equals("yes")){
                //cancellation object
                Cancellation cancellation = new Cancellation(ticketToCancel);
                bookingToCancel.setCancellation(cancellation); //associate cancellation with booking

                //cancel ticket and make refund
                if (cancellation.cancelTicket()){
                    //release booked seat
                    Showtime showtime = bookingToCancel.getShowtime();
                    String[] selectedSeats = bookingToCancel.getSelectedSeats();
                    if (showtime != null && selectedSeats != null) {
                        for (String seat : selectedSeats) {
                            int row = seat.charAt(0) - 'A';
                            int col = Integer.parseInt(seat.substring(1)) - 1;
                            showtime.releaseSeat(row, col);
                        }
                    }
                    System.out.println("Seat released successfully.");
                    //update bookingstatus???
                    bookingToCancel.setBookingStatus("Cancelled");
                }else{
                    System.out.println("Cancellation failed.");
                }
            }else{
                System.out.println("Cancellation aborted.");
            }
        }else if (bookingChoice != 0){
            System.out.println("Invalid booking choice.");

        }else{
            System.out.println("Returning to Staff Menu.");
        }
    }
}
