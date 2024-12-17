package com.example.loginpagedemo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import watchIt.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class MovieCardController {

    @FXML
    private ImageView MovieImage;

    @FXML
    private Label lblMovieName;
    @FXML
    private VBox BOX;

    public void setData(Movie movie) {
        String posterPath = movie.getPosterSrc();
        Image image = null;
        if (posterPath != null) {
            InputStream imageStream = getClass().getResourceAsStream(posterPath);
            if (imageStream != null) {
                image = new Image(imageStream);
            } else {
                System.out.println("Resource not found: " + posterPath);
            }
        } else {
            System.out.println("Poster path is null!");
        }
        MovieImage.setImage(image);
        lblMovieName.setText(movie.getTitle());

        BOX.setStyle("-fx-background-color: #"+ "white" +";" +
                " -fx-background-radius: 15;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.1), 10, 0 , 0 ,10);");
    }


    @FXML
    void Show_Movie_Details(MouseEvent event) throws IOException {


        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Test.fxml")); // Should load Scene1.fxml
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
