
public class Movie {
    
    private String title;
    private String genre;
    private String director;
    private String cast;
    private String language;
    private String duration;
    private double rating;
    private Showtime[] showtimes; // Compositon relationship with Showtime class
    private int showtimeCount;   // To keep track of the number of showtimes added
    private static final int MAX_SHOWTIMES = 5; // Maximum number of showtimes per movie object


    // Parameterized constructor
    public Movie(String title, String genre, String director, String cast, String language, String duration, double rating) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.cast = cast;
        this.language = language;
        this.duration = duration;

        // Validate rating (0.0 to 10.0)
        if (rating >= 0.0 && rating <= 10.0) {
            this.rating = rating;
        } else {
            throw new IllegalArgumentException("Rating must be between 0.0 and 10.0");
        }

        this.showtimes = new Showtime[MAX_SHOWTIMES]; 
        this.showtimeCount = 0;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    
    public String getDirector() {
        return director;
    }
    
    public void setDirector(String director) {
          this.director = director;
     }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {

        if (rating >= 0.0 && rating <= 10.0) {
            this.rating = rating;
        } else {
            throw new IllegalArgumentException("Rating must be between 0.0 and 10.0");
        }
    }

    public Showtime[] getShowtimes() {
        // Only return non-null showtimes
        Showtime[] validShowtimes = new Showtime[showtimeCount];
        for (int i = 0; i < showtimeCount; i++) {
            validShowtimes[i] = showtimes[i];
        }
        return validShowtimes;
    }

    public int getShowtimeCount() {
        return showtimeCount;
    }

    public void displayShowtimes() {
        System.out.println("Available Showtimes for " + title + ":");
        for (int i = 0; i < showtimeCount; i++) {
            System.out.println((i + 1) + ". " + showtimes[i].getDetails());
        }
    }

    // Method to add showtimes to the movie
    public void addShowtime(String time, String date, int hallNo, int rows, int columns) {
        if (showtimeCount < MAX_SHOWTIMES) {
            showtimes[showtimeCount] = new Showtime(time, date, hallNo, rows, columns);
            showtimeCount++;
        } else {
            System.out.println("Cannot add more showtimes. Maximum limit reached.");
        }
    }
    
    // Method to display movie details
    public String displayMovieInfo() {
        return  "Title:" + title + "\n" +
                "Genre:" + genre + "\n" +
                "Director:" + director + "\n" +
                "Cast:" + cast + "\n" +
                "Language:" + language + "\n" +
                "Duration:" + duration + "\n" +
                "Rating:" + rating + "\n";
    }

    public String getDetails() {
        return "Movie: " + title + ", Genre: " + genre + ", Rating: " + rating;
    }

}
