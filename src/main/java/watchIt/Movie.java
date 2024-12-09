package watchIt;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;



public class Movie implements Serializable {

    private int Id;
    private String Title;
    private LocalDate ReleaseDate;
    private int RunningTime;
    //ListCast
    //Director
    private String Genre;
    private ArrayList<String> Languages;
    private String Country;
    private float Budget;
    private float Revenue;
    private int Rating;
    private int Views;
    private String PosterSrc;

    public String getPosterSrc() {
        return PosterSrc;
    }
    public void setPosterSrc(String posterSrc) {
        PosterSrc = posterSrc;
    }

    public int getId() {return Id;}
    public void setId(int id) {Id = id;}

    public String getTitle() {return Title;}
    public void setTitle(String title) {Title = title;}

    public LocalDate getReleaseDate() {return ReleaseDate;}
    public void setReleaseDate(LocalDate releaseDate) {ReleaseDate = releaseDate;}

    public int getRunningTime() {return RunningTime;}
    public void setRunningTime(int runningTime) {RunningTime = runningTime;}

    public String getGenre() {return Genre;}
    public void setGenre(String genre) {Genre = genre;}

    public ArrayList<String> getLanguages() {return Languages;}
    public void setLanguages(ArrayList<String> languages) {Languages = languages;}

    public String getCountry() {return Country;}
    public void setCountry(String country) {Country = country;}

    public float getBudget() {return Budget;}
    public void setBudget(float budget) {Budget = budget;}

    public float getRevenue() {return Revenue;}
    public void setRevenue(float revenue) {Revenue = revenue;}

    public int getRating() {return Rating;}
    public void setRating(int rating) {Rating = rating;}

    public int getViews() {return Views;}
    public void setViews(int views) {Views = views;}

    private static int count = 1;
    public Movie(String title, LocalDate releaseDate, int runningTime, ArrayList<String> languages, String genre, String country, float budget) {
        Id = count++;
        Title = title;
        ReleaseDate = releaseDate;
        RunningTime = runningTime;
        Languages = languages;
        Genre = genre;
        Country = country;
        Budget = budget;
        Revenue = 0;
        Rating = 0;
        Views = 0;
    }
    public Movie(String title, String posterSrc) {
        Id = count++;
        Title = title;
        PosterSrc = posterSrc;
    }

    public static ArrayList<Movie> LoadMovieFromFile()
    {
        ArrayList<Movie> Movies = new ArrayList<>();
        File file = new File("Movies.txt");

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file)))
        {
            Movies = (ArrayList<Movie>) objectInputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        return Movies;
    }

    public static void SaveMoviesToFile(ArrayList<Movie> Movies)
    {
        File file = new File("Movies.txt");

        try (ObjectOutputStream objectOutputStream =new ObjectOutputStream(new FileOutputStream(file)))
        {
            objectOutputStream.writeObject(Movies);
        }
        catch (IOException e)
        {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static Movie Find(String movieTitle)
    {
        ArrayList<Movie> Movies = LoadMovieFromFile();

        for(Movie movie : Movies)
        {
            if(movie.getTitle().equals(movieTitle))
            {
                return movie;
            }
        }
        return null;
    }

    public static ArrayList<Movie> SameGenreMovies (String genre)
    {
        ArrayList<Movie> Movies = LoadMovieFromFile();
        ArrayList<Movie> SameGenre = new ArrayList<>();

        for(Movie movie : Movies)
        {
            if(movie.getGenre().contains(genre))
            {
                SameGenre.add(movie);
            }
        }
        return SameGenre;
    }

    public static Boolean IsMovieExist(String MovieTitle)
    {
        Movie movie = Movie.Find(MovieTitle);
        return (movie != null);

    }

    public static Boolean AddNewMovie(Movie movie)
    {
        ArrayList<Movie> Movies = LoadMovieFromFile();

        if(IsMovieExist(movie.getTitle()))
        {
            return false;
        }
        else
        {
            Movies.add(movie);
            Movie.SaveMoviesToFile(Movies);
            return true;
        }
    }

    public static Boolean DeleteMovie(Movie movie)
    {
        ArrayList<Movie> Movies = LoadMovieFromFile();

        if (IsMovieExist(movie.getTitle()))
        {
            Movies.remove(movie);
            Movie.SaveMoviesToFile(Movies);
            return true;
        }
        else
            return false;
    }

    public static ArrayList<Movie> TopViewedMovies()
    {
        ArrayList<Movie> Movies = LoadMovieFromFile();
        ArrayList<Movie> TopFiveMovies = new ArrayList<>();
        ArrayList<Movie> sortedMovies = (ArrayList<Movie>) Movies.stream().sorted(Comparator.comparingInt(Movie::getViews).reversed());

        for (int i =0;i<5;i++)
        {
            TopFiveMovies.add(sortedMovies.get(i));
        }
        return TopFiveMovies;
    }

}
