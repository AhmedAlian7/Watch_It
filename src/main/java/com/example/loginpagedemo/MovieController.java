package com.example.loginpagedemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import watchIt.Actor;
import watchIt.Movie;
import watchIt.UserWatchRecord;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
public class MovieController implements Initializable {
    @FXML
    private Label lblTitle;

    @FXML
    private HBox CardLayout;

    @FXML
    private ImageView imgPoster;

    @FXML
    private Label lblBudgut;

    @FXML
    private Label lblCountry;

    @FXML
    private Label lblDuration;

    @FXML
    private Label lblFormTitle;

    @FXML
    private Label lblGenre;

    @FXML
    private Label lblLanguage;

    @FXML
    private Label lblRating;

    @FXML
    private Label lblRealeseDate;
    @FXML
    private Hyperlink lblDirectorName;
    @FXML
    private Label lblViews;

    private Movie movie;

    @FXML
    void btnWatchLater_Clicked(ActionEvent event) {



    }

    @FXML
    void btnWatch_Clicked(ActionEvent event) {

        UserWatchRecord record = new UserWatchRecord(Global.CurrentUser.getID(),movie, LocalDate.now(),5f);

        Global.CurrentUser.WatchMovie(record);
    }

    public void setData(Movie selectedMovie) throws IOException {

        movie = selectedMovie;

        lblFormTitle.setText(selectedMovie.getTitle());


        String posterPath = selectedMovie.getPosterSrc();
        Image image = null;
        if (posterPath != null)
        {
            InputStream imageStream = getClass().getResourceAsStream(posterPath);
            if (imageStream != null)
                image = new Image(imageStream);
            else
            {
                System.out.println("Resource not found: " + posterPath);
                image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unknown.png")));
            }
        }
        else
        {
            System.out.println("Poster path is null!");
            image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unknown.png")));
        }

        imgPoster.setImage(image);

        lblTitle.setText(selectedMovie.getTitle());
        lblRealeseDate.setText(Integer.toString(selectedMovie.getReleaseDate().getYear()));
        lblDuration.setText(Integer.toString(selectedMovie.getRunningTime()) + " minutes");
        lblGenre.setText(selectedMovie.getGenre());
        lblLanguage.setText(selectedMovie.getLanguage());
        lblBudgut.setText(Float.toString(selectedMovie.getBudget()));
        lblCountry.setText(selectedMovie.getCountry());
        lblViews.setText(Integer.toString(selectedMovie.getViews()));
        lblRating.setText(Float.toString(selectedMovie.getRating()));
        if (selectedMovie.getActors() != null)
            lblDirectorName.setText(selectedMovie.getDirector().getFullName());

        if (selectedMovie.getActors() != null)
            DisplayCast(selectedMovie.getActors());
    }

    private void DisplayCast(ArrayList<Actor> actors) throws IOException {

        for (Actor value : actors) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ActorCard.fxml"));
            AnchorPane cardBox = fxmlLoader.load(); // exception !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            ActorCardController cardController = fxmlLoader.getController();
            cardController.setData(value);
            CardLayout.getChildren().add(cardBox);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
