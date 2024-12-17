package watchIt;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Movie implements Serializable {

    private int Id;
    private String Title;
    private LocalDate ReleaseDate;
    private int RunningTime;
    private String Genre;
    private String Language;
    private String Country;
    private float Budget;
    private float Revenue;
    private float Rating;
    private int Views;
    private String PosterSrc;

    private ArrayList<Actor> Actors;
    private Director Director;

    private float OldRatings;
    private int noofwatched;

    public ArrayList<Actor> getActors() {
        return Actors;
    }

    public void setActors(ArrayList<Actor> cast) {
        Actors = cast;
    }

    public Director getDirector() {
        return Director;
    }

    public void setDirector(Director director) {
        Director = director;
    }

    public static ArrayList<Movie> Movies;

    public String getPosterSrc() {
        return PosterSrc;
    }

    public void setPosterSrc(String posterSrc) {
        PosterSrc = posterSrc;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public LocalDate getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        ReleaseDate = releaseDate;
    }

    public int getRunningTime() {
        return RunningTime;
    }

    public void setRunningTime(int runningTime) {
        RunningTime = runningTime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String languages) {
        Language = languages;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public float getBudget() {
        return Budget;
    }

    public void setBudget(float budget) {
        Budget = budget;
    }

    public float getRevenue() {
        return Revenue;
    }

    public void setRevenue(float revenue) {
        Revenue = revenue;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public int getViews() {
        return Views;
    }

    public void setViews(int views) {
        Views = views;
    }

    private static int count = 1;

    public Movie() {
        Id = count++;
    }
    public Movie(String title, LocalDate releaseDate, int runningTime, String languages, String genre, String country, float budget) {
        Id = count++;
        Title = title;
        ReleaseDate = releaseDate;
        RunningTime = runningTime;
        Language = languages;
        Genre = genre;
        Country = country;
        Budget = budget;
        Revenue = 0;
        Rating = 0;
        Views = 0;

        Movies = LoadMovieFromFile();
    }

    public Movie(int id, String title, String genre, int runningTime, float budget, String country, String language, LocalDate releaseDate, float revenue, int views) {


        Id = id;
        Title = title;
        Genre = genre;
        RunningTime = runningTime;
        Budget = budget;
        Country = country;
        Language = language;
        ReleaseDate = releaseDate;
        Revenue = revenue;
        Views = views;
    }
    public Movie(String title, String genre, int runningTime, float budget, String country, String language, LocalDate releaseDate, float revenue, int views , Director director , ArrayList<Actor> actors , String posterSrc ) {
        Id = count++;
        Title = title;
        Genre = genre;
        RunningTime = runningTime;
        Budget = budget;
        Country = country;
        Language = language;
        ReleaseDate = releaseDate;
        Revenue = revenue;
        Views = views;
        Director = director;
        Actors = actors;
        PosterSrc = posterSrc;
    }

    public static ArrayList<Movie> LoadMovieFromFile() {
        ArrayList<Movie> Movies = new ArrayList<>();
        File file = new File("Movies.txt");

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            Movies = (ArrayList<Movie>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        return Movies;
    }

    public static void SaveMoviesToFile(ArrayList<Movie> Movies) {
        File file = new File("Movies.txt");

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(Movies);
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static Movie Find(String movieTitle) {
        ArrayList<Movie> Movies = LoadMovieFromFile();

        for (Movie movie : Movies) {
            if (movie.getTitle().equalsIgnoreCase(movieTitle)) {
                return movie;
            }
        }
        return null;
    }

    public static ArrayList<Movie> SameGenreMovies(String genre) {
        ArrayList<Movie> Movies = LoadMovieFromFile();
        ArrayList<Movie> SameGenre = new ArrayList<>();

        for (Movie movie : Movies) {
            if (movie.getGenre().contains(genre)) {
                SameGenre.add(movie);
            }
        }
        return SameGenre;
    }

    public static Boolean IsMovieExist(String MovieTitle) {
        Movie movie = Movie.Find(MovieTitle);
        return (movie != null);

    }

    public static Boolean AddNewMovie(Movie movie) {
        ArrayList<Movie> Movies = LoadMovieFromFile();

        if (IsMovieExist(movie.getTitle())) {
            return false;
        } else {
            Movies.add(movie);
            Movie.SaveMoviesToFile(Movies);
            return true;
        }
    }

    public static Boolean DeleteMovie(Movie movie) {
        ArrayList<Movie> Movies = LoadMovieFromFile();

        if (IsMovieExist(movie.getTitle())) {
            Movies.remove(movie);
            Movie.SaveMoviesToFile(Movies);
            return true;
        } else
            return false;
    }

    public static ArrayList<Movie> getMostViewedMovie(int limit) {

        ArrayList<Movie> Movies = LoadMovieFromFile();
        ArrayList<Movie> TopFiveMovies = new ArrayList<>();

        if (Movies == null || Movies.isEmpty()) {
            System.out.println("No movies available to process.");
            return TopFiveMovies;
        }

        List<Movie> sortedMovies = Movies.stream()
                .sorted(Comparator.comparingInt(Movie::getViews).reversed())
                .toList();

        for (int i = 0; i < Math.min(limit, sortedMovies.size()); i++) {
            TopFiveMovies.add(sortedMovies.get(i));
        }

        return TopFiveMovies;
    }

    public static ArrayList<Movie> getRecentMovies() {
        ArrayList<Movie> movies = LoadMovieFromFile();
        ArrayList<Movie> recentMovies = new ArrayList<>();

        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);

        for (Movie movie : movies) {
            if (movie.getReleaseDate().isAfter(oneMonthAgo)) {
                recentMovies.add(movie);
            }
        }

        recentMovies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return m2.getReleaseDate().compareTo(m1.getReleaseDate());
            }
        });

        return recentMovies;

    }

    public static List<Movie> getTopRatedMovies(int limit) {
        List<Movie> movies = Movies;
        List<Movie> topRatedMovies = new ArrayList<>(movies);

        // Sort movies by rating in descending order
        topRatedMovies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return Float.compare(m2.getRating(), m1.getRating());
            }
        });

        if (topRatedMovies.size() > limit) {
            return topRatedMovies.subList(0, limit);
        }

        return topRatedMovies;
    }

    public void CalcRating(int rating) {
        noofwatched++;
        Views++; // When press watch
        OldRatings += rating;
        this.Rating = OldRatings / noofwatched;

    }

    public static ArrayList<Movie> Filter(String word) { // word : movie name or actor name or genre

        ArrayList<Movie> AllMovies = LoadMovieFromFile();
        ArrayList<Movie> FilteredMovies = new ArrayList<>();
        for (Movie movie : AllMovies) {
            if (movie.getTitle().contains(word) || movie.getGenre().contains(word))
                FilteredMovies.add(movie);
            for (Cast actor : movie.getActors()) {
                if (actor.getFullName().contains(word))
                    FilteredMovies.add(movie);
            }
        }
        return FilteredMovies;
    }

    public static ArrayList<Movie> CopyMovies() {
        ArrayList<Movie> FileMovies = Movie.LoadMovieFromFile();
        ArrayList<Movie> guiMovies = new ArrayList<>();

        for (Movie movie : FileMovies) {
            guiMovies.add(new Movie(
                    movie.getId(),
                    movie.getTitle(),
                    movie.getGenre(),
                    movie.getRunningTime(),
                    movie.getBudget(),
                    movie.getCountry(),
                    movie.getLanguage(),
                    movie.getReleaseDate(),
                    movie.getRevenue(),
                    movie.getViews()
            ));


        }
        return guiMovies;


    }
}
