package com.example.loginpagedemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import watchIt.*;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml"))); // Should load Scene1.fxml
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {

//        Director di = new Director("Ahmed", "Alian", LocalDate.of(2014,12,1), Person.enGender.Male,"Syrian");
//        ArrayList<Actor> actors = new ArrayList<>();
//       actors.add(new Actor("Ahmed", "Alian",  LocalDate.of(2014,12,1), Person.enGender.Male,"Syrian"));
//        actors.add(new Actor("Amr", "Ahmed",  LocalDate.of(2014,12,1), Person.enGender.Male,"Syrian"));
//        actors.add(new Actor("Omar", "Alian",  LocalDate.of(2014,12,1), Person.enGender.Male,"Syrian"));
//
//       Movie movie = new Movie("Interstellar", "Action",125,12000,"US","English", LocalDate.of(2014,12,1), "/img/interstellar.jpg",di,actors);
//       Movie.AddNewMovie(movie);
//
//
//        movie = new Movie("Joker", "Action",141,1256,"US","English", LocalDate.of(2020,12,1), "/img/joker.jpg",di,actors);
//        Movie.AddNewMovie(movie);



        //launch();
    }
}
