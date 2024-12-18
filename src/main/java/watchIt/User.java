package watchIt;

import java.io.*;
import java.util.ArrayList;

public class User implements Serializable {

    private int ID;
    private String Username;
    private String Password;
    private String FirstName;
    private String LastName;
    private String Email;

    private ArrayList<Movie> WatchedMovies;
    private ArrayList<Movie> WatchLaterMovies;
    private ArrayList<UserWatchRecord> userWatchRecord;

    private ArrayList<Subscription> subscriptionHistory;
    private Subscription currentSubscription;

    private int limitMovies;

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFullName() {
        return FirstName + " " + LastName;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }

    public Subscription getSubscription() {
        return currentSubscription;
    }
    public void setSubscription(Subscription subscription) {
        this.currentSubscription = subscription;
    }

    private static int counter =1;

    public User(String username, String email, String lastName, String firstName, String password) {
        Username = username;
        this.ID = counter;
        Email = email;
        LastName = lastName;
        FirstName = firstName;
        Password = password;

        WatchedMovies = new ArrayList<>();
        WatchLaterMovies = new ArrayList<>();
        userWatchRecord = new ArrayList<>();
        currentSubscription = new Subscription();
        counter++;
    }

    public static ArrayList<User> LoadUsersFromFile() throws IOException {

        ArrayList<User> users = new ArrayList<>();
        File file = new File("Users.txt");

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            users = (ArrayList<User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        return users;
    }
    public static void saveUsersDataToFile(ArrayList<User> users) {
        File file = new File("Users.txt");

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(users);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public static User Find(String Username) throws IOException {
        ArrayList<User> users = LoadUsersFromFile();

        for (User user : users) {
            if (user.Username.equals(Username))
                return user;
        }
        return null;
    }
    public static User Find(String Username, String Password) throws IOException {
        ArrayList<User> users = LoadUsersFromFile();

        for (User user : users) {
            if (user.Username.equals(Username) && user.Password.equals(Password))
                return user;
        }
        return null;
    }
    public static User Find(int ID) throws IOException {
        ArrayList<User> users = LoadUsersFromFile();

        for (User user : users) {
            if (user.ID == ID)
                return user;
        }
        return null;
    }
    public static boolean isUserExist(String Username) throws IOException {
        User user = Find(Username);
        return (user != null);
    }
    public static boolean isUserExist(int ID) throws IOException {
        User user = Find(ID);
        return (user != null);
    }
    public static boolean isUserExist(String Username, String Password) throws IOException {
        User user = Find(Username,Password);
        return (user != null);
    }

    public static boolean AddNewUser(User newUser) throws IOException {

        ArrayList<User> users = LoadUsersFromFile();

        if (isUserExist(newUser.getUsername())) {
            return false;
        }

        users.add(newUser);
        saveUsersDataToFile(users);

        return true;
    }

    public static boolean Delete(int ID) throws IOException {

        ArrayList<User> users = LoadUsersFromFile();

        User user = Find(ID);

        if (user == null)
            return false;

        users.remove(user);
        saveUsersDataToFile(users);
        return true;
    }

    public Boolean hasValidSups() {
        return true;
    }
    public void createNewSups(Subscription subscription) {
        subscriptionHistory.add(currentSubscription);
        currentSubscription = subscription;
        limitMovies = currentSubscription.getAllowedWatches();

    }

    public void WatchMovie(UserWatchRecord record) {

        userWatchRecord.add(record);

    }


}