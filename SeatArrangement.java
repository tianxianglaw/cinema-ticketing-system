public class SeatArrangement {
    private String[][] seats; // 2D array for rows and columns of seats
    private int rows;
    private int columns;

    public SeatArrangement(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = new String[rows][columns]; // Initialize the seat grid

        // Assign seat IDs (e.g., A1, A2, ...) to each seat
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seats[i][j] = (char) ('A' + i) + String.valueOf(j + 1); // Row letter + Column number
            }
        }
    }

    public int getRows(){
        return rows;
    }

    public int getColumns(){
        return columns;
    }

    public String[][] getSeats(){
        return seats;
    }

    //check the seat row n column is within the valid range
    public boolean isSeatValid(int row, int col){
        return row >= 0 && row < rows && col >= 0 && col < columns;
    }

    // Display the seat arrangement
    public void displaySeats() {
        System.out.println("Seats: (B = Booked)");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(seats[i][j] + " "); // Print seat ID or status
            }
            System.out.println(); // Move to the next row
        }
    }

    // Book a specific seat
    public boolean bookSeat(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns && !seats[row][col].equals("B")) {
            seats[row][col] = "B"; // Mark the seat as Booked
            return true;
        }
        return false; // Seat not available
    }

    // Cancel a specific seat booking
    public boolean cancelSeatBooking(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns && seats[row][col].equals("B")) {
            seats[row][col] = (char) ('A' + row) + String.valueOf(col + 1); // Reset to original seat ID
            return true;
        }
        return false; // Invalid or not booked
    }

    public void releaseSeat (int row, int col){
        if (isSeatValid(row,col)&& seats[row][col].equals("B")){
            seats[row][col] = (char)('A'+row)+ String.valueOf(col+1); // reset to ori seat ID
        }
    }
}
