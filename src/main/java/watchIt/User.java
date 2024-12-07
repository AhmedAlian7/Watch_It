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
    private Subscription.enPlan subscriptionType;

    public Subscription.enPlan getSubscriptionType() {
        return subscriptionType;
    }
    public void setSubscriptionType(Subscription.enPlan subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

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

    private static int counter =1;

    public User(String username, Subscription.enPlan subscription, String email, String lastName, String firstName, String password) {
        Username = username;
        this.ID = counter;
        this.subscriptionType = subscription;
        Email = email;
        LastName = lastName;
        FirstName = firstName;
        Password = password;

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
    public static boolean isUserExist(String Username) throws IOException {
        User user = Find(Username);
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


    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", subscriptionType=" + subscriptionType +
                '}';
    }
}