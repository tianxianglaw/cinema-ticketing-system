import java.io.*; //file handling
import java.util.Scanner; //import scanner 

public class SalesReport {
    private static final String BOOKING_HISTORY_FILE = "March_report.txt"; //declare booking history file name 

    public static void generateSalesReport(CinemaStaff admin) {  
        double totalProfit = 0; //initialise the total profit 

        File file = new File(BOOKING_HISTORY_FILE); //create file object 
        if (!file.exists()) { //if the booking history not exist
            System.out.println("Error: Booking history file not found!"); //prompt error message 
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKING_HISTORY_FILE))) { //buffer reader for efficient file reading
            String line; //set line 
            while ((line = reader.readLine()) != null) { //loop file until no more line exist
                String[] details = line.split(","); //identify the different value with using ,

                if (details.length >= 7) { //check the line contains at least 7 field
                    try {
                        double ticketPrice = Double.parseDouble(details[7].trim()); //get the value in the file
                        totalProfit += ticketPrice; //sum of the ticket price
                    } catch (NumberFormatException e) {
                        continue; //ignore invalid data and move to next record
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage()); //prompt error message 
            return;
        }

        Scanner scanner = new Scanner(System.in); //scanner object 
        System.out.println("\n========================================");
        System.out.println("|              Sales Report            |");
        System.out.println("========================================");
        System.out.println("|                                      |");
        System.out.println("| Total profit earned: RM" + totalProfit + "        |"); //show the total prifit
        System.out.println("|                                      |");
        System.out.println("========================================");

        System.out.print("Enter any number to go back to the Report Menu: "); //prompt back to menu message 
        scanner.nextInt(); //read user input 
        scanner.nextLine(); //clear buffer

        Reporting.ReportMenu(admin); //return to report menu
    }
}
