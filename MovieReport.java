import java.io.*; //file handling
import java.util.HashMap; //map classes to store movie profit
import java.util.Map; //map classes to store movie profit
import java.util.Scanner; //import scanner

public class MovieReport {
    private static final String BOOKING_HISTORY_FILE = "March_report.txt"; //declare the booking history file name

    public static void generatePopularMovieReport(CinemaStaff admin) { 
        Map<String, Double> movieProfits = new HashMap<>(); //create hashmap to store each movie profit

        movieProfits.put("Inception", 0.0); //initialise the inception profit 
        movieProfits.put("The Dark Knight", 0.0); //initialise the the dark knight profit 
        movieProfits.put("Interstellar", 0.0); //initialise the interstellar profit 

        File file = new File(BOOKING_HISTORY_FILE); //create file object
        if (!file.exists()) { //if file not exists
            System.out.println("Error: Booking history file not found!"); //prompt error message
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKING_HISTORY_FILE))) {
            String line; //set line
            while ((line = reader.readLine()) != null) { //loop file until no more line exist
                String[] details = line.split(","); //identify the different value with using ,

                if (details.length >= 7) { //check the line contains at least 7 field
                    String movieTitle = details[1].trim(); //get the movie name 
                    try {
                        double ticketPrice = Double.parseDouble(details[7].trim()); //get ticket price
                        if (movieProfits.containsKey(movieTitle)) {
                            movieProfits.put(movieTitle, movieProfits.get(movieTitle) + ticketPrice); //check movie aname exist in the profit hashmap or not
                        }
                    } catch (NumberFormatException e) {
                        continue; //ignore invalid data and move to next record
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage()); //prompt error show error type
            return;
        }

        Scanner scanner = new Scanner(System.in); //create scanner object
        System.out.println("\n========================================");
        System.out.println("|         Popular Movie Report         |");
        System.out.println("========================================");
        System.out.println("|                                      |");
        System.out.println("| 1. Movie Name  : Inception           |");
        System.out.println("|    Total Profit: RM" + movieProfits.get("Inception") + "            |"); //interception movie profit 
        System.out.println("| 2. Movie Name  : The Dark Knight     |");
        System.out.println("|    Total Profit: RM" + movieProfits.get("The Dark Knight") + "            |"); //the dark knight movie profit
        System.out.println("| 3. Movie Name  : Interstellar        |");
        System.out.println("|    Total Profit: RM" + movieProfits.get("Interstellar") + "            |"); //interstellar movie profit
        System.out.println("|                                      |");
        System.out.println("========================================");

        System.out.print("Enter any number to go back to the Report Menu: "); //prompt user input message
        scanner.nextInt(); //read input
        scanner.nextLine(); //clear buffer

        Reporting.ReportMenu(admin); //back to report menu
    }
}
