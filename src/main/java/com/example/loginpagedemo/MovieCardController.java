package com.example.loginpagedemo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import watchIt.Movie;

public class MovieCardController {

    @FXML
    private ImageView MovieImage;

    @FXML
    private Label lblMovieName;

    public void setData(Movie movie) {
        Image image = new Image(getClass().getResourceAsStream(movie.getPosterSrc()));
        MovieImage.setImage(image);
        lblMovieName.setText(movie.getTitle());
    }

}
