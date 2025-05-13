import java.util.Scanner; //import scanner 

public class PeakTimeReport {
    public static void generatePeakTimeReport(CinemaStaff admin) { 
        Scanner scanner = new Scanner(System.in); //create scanner object
        System.out.println("\n========================================");
        System.out.println("|           Peak Time Report           |");
        System.out.println("========================================");
        System.out.println("|                                      |");
        System.out.println("| 1. Monday   : 4.00PM - 5.00PM        |");
        System.out.println("| 2. Tuesday  : 4.00PM - 5.00PM        |");
        System.out.println("| 3. Wednesday: 4.00PM - 5.00PM        |");
        System.out.println("| 4. Thursday : 4.00PM - 5.00PM        |");
        System.out.println("| 5. Friday   : 10.00PM - 11.00PM      |");
        System.out.println("| 6. Saturday : 12.00PM - 1.00PM       |");
        System.out.println("| 7. Sunday   : 11.00PM - 12.00PM      |");
        System.out.println("|                                      |");
        System.out.println("========================================");

        System.out.print("Enter any number to go back to the Report Menu: "); //prompt input message
        scanner.nextInt(); //read input 
        scanner.nextLine(); //clear buffer

        Reporting.ReportMenu(admin); //back to report menu
    }
}
