package com.example.loginpagedemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import watchIt.Movie;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminController implements Initializable {



    @FXML
    private TableView<Movie> MoviesView;

    @FXML
    private TableColumn<Movie , Integer> Id;

    @FXML
    private TableColumn<Movie , String> Title;

    @FXML
    private TableColumn<Movie , String> Genre;

    @FXML
    private TableColumn<Movie , Float> RunningTime;

    @FXML
    private TableColumn<Movie , Float> Budget;

    @FXML
    private TableColumn<Movie , String> Country;

    @FXML
    private TableColumn<Movie , String> Language;

    @FXML
    private TableColumn<Movie , LocalDate> ReleaseDate;

    @FXML
    private TableColumn<Movie , Float> Revenue;

    @FXML
    private TableColumn<Movie , Integer> Views;

    @FXML
    private ContextMenu contextMenu;




    @FXML
    private Button btn_Movies;

    @FXML
    private Button btn_Users;

    @FXML
    private Button btn_Subscriptions;

    @FXML
    private Button AddMovie;

    public AdminController() throws IOException {
    }


    public void btn_Movies_Clicked(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Movies");
        stage.show();

    }

    public void btn_Users_Clicked(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Admin_Users.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Users");
        stage.show();
        
    }

    public static Parent rootaddmovie;
    static {
        try {
            rootaddmovie = FXMLLoader.load(AdminController.class.getResource("AddMovie.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Stage stageaddmovie = new Stage();
    public static Scene sceneaddmovie = new Scene(rootaddmovie);
    public void btn_AddMovie_Clicked(ActionEvent event) throws IOException
    {

        stageaddmovie.setScene(sceneaddmovie);
        stageaddmovie.setTitle("Add Movie");
        stageaddmovie.show();

    }


    ArrayList<Movie> movies = Movie.CopyMovies();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




        ObservableList<Movie> list = FXCollections.observableArrayList(movies);

        if (Id != null) {
            Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        } else {
            System.err.println("Id TableColumn is null!");
        }

        if (Title != null) {
            Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        } else {
            System.err.println("Title TableColumn is null!");
        }

        if (Genre != null) {
            Genre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        } else {
            System.err.println("Genre TableColumn is null!");
        }

        if (RunningTime != null) {
            RunningTime.setCellValueFactory(new PropertyValueFactory<>("RunningTime"));
        } else {
            System.err.println("RunningTime TableColumn is null!");
        }

        if (Budget != null) {
            Budget.setCellValueFactory(new PropertyValueFactory<>("Budget"));
        } else {
            System.err.println("Budget TableColumn is null!");
        }

        if (Country != null) {
            Country.setCellValueFactory(new PropertyValueFactory<>("Country"));
        } else {
            System.err.println("Country TableColumn is null!");
        }

        if (Language != null) {
            Language.setCellValueFactory(new PropertyValueFactory<>("Language"));
        } else {
            System.err.println("Language TableColumn is null!");
        }

        if (ReleaseDate != null) {
            ReleaseDate.setCellValueFactory(new PropertyValueFactory<>("ReleaseDate"));
        } else {
            System.err.println("ReleaseDate TableColumn is null!");
        }

        if (Revenue != null) {
            Revenue.setCellValueFactory(new PropertyValueFactory<>("Revenue"));
        } else {
            System.err.println("Revenue TableColumn is null!");
        }

        if (Views != null) {
            Views.setCellValueFactory(new PropertyValueFactory<>("Views"));
        } else {
            System.err.println("Views TableColumn is null!");
        }



        if (MoviesView != null) {
            MoviesView.setItems(list);
        } else {
            System.err.println("MoviesView TableView is null!");
        }
        if (MoviesView != null) {
            MoviesView.setVisible(true);
        } else {
            System.err.println("MoviesView TableView is null!");
        }
    }

    public void Deletebook(ActionEvent event){

        Movie movie = MoviesView.getSelectionModel().getSelectedItem();
        if (movie != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Confirmation");
            confirmationAlert.setHeaderText("Are you sure you want to delete this movie?");
            confirmationAlert.setContentText("Movie: " + movie.getTitle());

            ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

            if (result == ButtonType.OK) {

                MoviesView.getItems().remove(movie);

                movies.remove(movie);

                Movie.DeleteMovie(movie);

                MessageBox.showConfirmation("Success","Movie deleted successfully.");
            } else {
                MessageBox.showError("Cancelled","Movie delete Cancelled.");
            }
        } else {
            MessageBox.showError("Error","No movie selected for deletion.");
        }
    }
}


