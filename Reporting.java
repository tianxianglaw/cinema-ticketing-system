import java.util.Scanner; //import scanner

public class Reporting{
	public static void ReportMenu(CinemaStaff admin){
		Scanner scanner = new Scanner(System.in); //create scanner object 
    	System.out.println("\n========================================");
    	System.out.println("|              Report Menu             |");
    	System.out.println("========================================");
    	System.out.println("| 1. Sales Report                      |");
    	System.out.println("| 2. Popular Movie Report              |");
    	System.out.println("| 3. Peak Hour Report                  |");
    	System.out.println("| 4. Back To Staff Menu                |");
    	System.out.println("========================================");
    
    	System.out.print("Please enter your option: "); //prompt user enter choice 
    	int report_choice = scanner.nextInt(); //read user input
    	scanner.nextLine(); //clear buffer
    
    	switch(report_choice){
    		case 1:
        		SalesReport.generateSalesReport(admin); //go to sales report
            	break;
        	case 2:
        		MovieReport.generatePopularMovieReport(admin); //go to movie report
            	break;
        	case 3:
            	PeakTimeReport.generatePeakTimeReport(admin); //go to peak time report
            	break;
        	case 4:
            	CinemaSystem.staffPage(admin); //back to staff page 
            	break;
        	default:
            	System.out.println("Invalid option. Please try again."); //invalid input 
    	}
	}
}