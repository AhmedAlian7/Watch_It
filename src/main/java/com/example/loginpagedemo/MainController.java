package com.example.loginpagedemo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import watchIt.Movie;
import javafx.scene.input.MouseEvent;
import  javafx.scene.control.TextField;


import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    private ArrayList<Movie> recentMovies;
    private ArrayList<Movie> MostViewsMovies;


    @FXML
    private HBox CardLayout,CardLayout1;
    @FXML
    private GridPane MovieContainer;
    @FXML
    private GridPane SearchContainer;
    @FXML
    private Pane pan_Home;
    @FXML
    private Pane pan_Search;
    @FXML
    private TextField FuckingSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //lblUserName.setText(Global.CurrentUser.getFullName());
        pan_Home.setVisible(true);
        pan_Search.setVisible(false);
        try {
            Display_RecentMovies();
            Display_TrendingMovies();
        }
        catch (IOException ex) {
            System.err.println("Failed operation, Can't get recent movies or trending movies");
        }

    }
    public void Display_RecentMovies() throws IOException {

        recentMovies =new ArrayList<>(Movie.LoadMovieFromFile());
        for (Movie value : recentMovies) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
            VBox cardBox = fxmlLoader.load(); // exception !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            MovieCardController cardController = fxmlLoader.getController();
            cardController.setData(value);
            CardLayout.getChildren().add(cardBox);
        }
    }
    public void Display_TrendingMovies() throws IOException {


        //MostViewsMovies = Movie.getMostViewedMovie(24);
        MostViewsMovies = new ArrayList<>(Movie.LoadMovieFromFile());
        /*int column = 8;
        int row = 1;

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

        }*/
        for (Movie value : MostViewsMovies) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
            VBox cardBox = fxmlLoader.load(); // exception !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            MovieCardController cardController = fxmlLoader.getController();
            cardController.setData(value);
            CardLayout1.getChildren().add(cardBox);
        }
    }


    @FXML
    void Categories_Clicked(MouseEvent event) {

    }

    @FXML
    void History_Clicked(MouseEvent event) {

    }

    @FXML
    void Home_Clicked(MouseEvent event) {
        pan_Home.setVisible(true);
        pan_Search.setVisible(false);
        try {
            Display_RecentMovies();
            Display_TrendingMovies();
        }
        catch (IOException ex) {
            System.err.println("Failed operation, Can't get recent movies or trending movies");
        }

    }

    @FXML
    void Recommended_Clicked(MouseEvent event) {

    }

    @FXML
    void Search_Clicked(MouseEvent event) {
        pan_Home.setVisible(false);
        pan_Search.setVisible(true);
    }

    @FXML
    void WatchLater_Clicked(MouseEvent event) {

    }

    @FXML
    void btnSearch_Clicked(MouseEvent event) throws IOException {
        String word = FuckingSearch.getText();
        ArrayList<Movie> searchedMovies = Movie.Filter(word);
        int column = 8;
        int row = 1;

        for(Movie movie :  searchedMovies){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource( "Card.fxml"));
            VBox cardBox = fxmlLoader.load();
            MovieCardController cardController = fxmlLoader.getController();
            cardController.setData(movie);

            if (column == 6) {
                column =0;
                row++;
            }

            SearchContainer.add(cardBox, column++,row);
            GridPane.setMargin(cardBox, new Insets(10));

        }

    }

}
