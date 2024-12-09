package com.example.loginpagedemo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import watchIt.Movie;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    private ArrayList<Movie> recentMovies;
    private ArrayList<Movie> MostViewsMovies;


    @FXML
    private HBox CardLayout;
    @FXML
    private GridPane MovieContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int column = 8;
        int row = 1;

        MostViewsMovies = Movie.TopViewedMovies();
        try {
            for (Movie value : recentMovies) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
                HBox cardBox = fxmlLoader.load();
                MovieCardController cardController = fxmlLoader.getController();
                cardController.setData(value);
                CardLayout.getChildren().add(cardBox);
            }

            for(Movie movie :  MostViewsMovies){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource( "Card.fxml"));
                VBox cardBox = fxmlLoader.load();
                MovieCardController cardController = fxmlLoader.getController();
                cardController.setData(movie);

                if (column == 6) {
                    column =0;
                    row++;
                }

                MovieContainer.add(cardBox, column++,row);
                GridPane.setMargin(cardBox, new Insets(10));

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
