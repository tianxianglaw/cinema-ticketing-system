public class Showtime {
    
    private String date;
    private String time;
    private int hallNo;
    private SeatArrangement seatArrangement;

    //Parameterized constructor
    public Showtime(String time, String date, int hallNo, int rows, int columns) {
        this.time = time;
        this.date = date;
        this.hallNo = hallNo;
        this.seatArrangement = new SeatArrangement(rows, columns);
    }

    // Getters 
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getHallNo() {
        return hallNo;
    }

    public SeatArrangement getSeatArrangement() {
        return seatArrangement;
    }

    // Display showtime details
    public String getDetails() {
        return "Date: " + date + ", Time: " + time + ", Hall No: " + hallNo;
    }
    

    // Display Seats
    public void displaySeats() {
        System.out.println("Seat Arrangement for Hall " + hallNo + ":");
        seatArrangement.displaySeats(); // Display the seat arrangement
    }

    // Book a seat
    public boolean bookSeat(int row, int col) {
        return seatArrangement.bookSeat(row, col);
    }

    public boolean isSeatValid(int row, int col){
        return seatArrangement.isSeatValid(row,col);
    }
    // Cancel a seat booking
    public boolean cancelSeatBooking(int row, int col) {
        return seatArrangement.cancelSeatBooking(row, col);
    }
    public void releaseSeat(int row, int col){
        seatArrangement.releaseSeat(row,col);
    }

}
