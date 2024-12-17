package com.example.loginpagedemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import watchIt.Actor;
import watchIt.Director;
import watchIt.Movie;
import watchIt.Person;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddMovieController implements Initializable {

    @FXML
    private TextField Budget;

    @FXML
    private TextField Country;

    @FXML
    private DatePicker DDOB;

    @FXML
    private TextField DFname;

    @FXML
    private ChoiceBox<Person.enGender> DGender;

    @FXML
    private TextField DLname;

    @FXML
    private TextField DNation;

    @FXML
    private TextField Genre;

    @FXML
    private TextField Language;

    @FXML
    private DatePicker ReleaseDate;

    @FXML
    private TextField Revenue;

    @FXML
    private TextField RunningTime;

    @FXML
    private TextField Title;

    @FXML
    private TextField Views;

    @FXML
    private TextField PosterPath;


    @FXML
    private DatePicker ADOB;

    @FXML
    private TextField AFname;

    @FXML
    private ChoiceBox<Person.enGender> AGender;

    @FXML
    private TextField ALname;

    @FXML
    private TextField ANation;

    public AddMovieController() throws IOException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        if (DGender!=null) {
            DGender.getItems().addAll(Person.enGender.Male, Person.enGender.Female);
        }
        if (AGender!=null) {
            AGender.getItems().addAll(Person.enGender.Male, Person.enGender.Female);
        }


    }

    public static Parent roota;
    static {
        try {
            roota = FXMLLoader.load(AdminController.class.getResource("AddActor.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Stage stagea = new Stage();
    public static Scene scenea = new Scene(roota);
    public void btn_AddActor_Clicked(ActionEvent event) throws IOException {
        stagea.setScene(scenea);
        stagea.setTitle("Add Actor");
        stagea.showAndWait();
    }

    public ArrayList<Actor> actors = new ArrayList<>();

    public void btn_SaveActor_Clicked(ActionEvent event) throws IOException
    {

        if (AFname.getText().isEmpty() || ALname.getText().isEmpty() || ADOB.getValue() == null || AGender.getValue() == null || ANation.getText().isEmpty()) {
            MessageBox.showError("Error", "Please fill in all the actor details.");
        }

        String afname = AFname.getText();
        String alname = ALname.getText();
        LocalDate adob = ADOB.getValue();
        Person.enGender agender = AGender.getValue();
        String anation = ANation.getText();

        Actor actor = new Actor(afname, alname, adob, agender, anation);
        actors.add(actor);

        AFname.clear();
        ALname.clear();
        ADOB.setValue(null);
        AGender.setValue(null);
        ANation.clear();

        stagea.close();
        MessageBox.showConfirmation("Success", "Actor added successfully!");
    }

    private boolean InEdit = false;

    public void btn_Save_Clicked(ActionEvent event) throws IOException {

            if (DFname.getText().isEmpty() || DLname.getText().isEmpty() || DDOB.getValue() == null || DGender.getValue() == null || DNation.getText().isEmpty() ||
                    Title.getText().isEmpty() || Genre.getText().isEmpty() || RunningTime.getText().isEmpty() || Budget.getText().isEmpty() ||
                    Country.getText().isEmpty() || Language.getText().isEmpty() || ReleaseDate.getValue() == null || Revenue.getText().isEmpty() || Views.getText().isEmpty() ||
                    PosterPath.getText().isEmpty()) {
                MessageBox.showError("Error", "Please fill in all movie and director details.");
                return;
            }

            String dfname = DFname.getText();
            String dlname = DLname.getText();
            LocalDate ddob = DDOB.getValue();
            Person.enGender dgender = DGender.getValue();
            String dnation = DNation.getText();



            String title = Title.getText();
            String genre = Genre.getText();
            int runningTime = Integer.parseInt(RunningTime.getText());
            float budget = Float.parseFloat(Budget.getText());
            String country = Country.getText();
            String language = Language.getText();
            LocalDate releaseDate = ReleaseDate.getValue();
            float revenue = Float.parseFloat(Revenue.getText());
            int views = Integer.parseInt(Views.getText());
            String posterPath = PosterPath.getText();



            Director director = new Director(dfname, dlname, ddob, dgender, dnation);

            Movie movie = new Movie(title, releaseDate,runningTime, language, genre, country, budget);

            Movie.AddNewMovie(movie);

            if (Movie.IsMovieExist(title)) {
                MessageBox.showConfirmation("Success", "The movie added successfully!");
                AdminController.stageaddmovie.close();
            } else {
                MessageBox.showError("Failed", "Something went wrong.");
            }
    }












}
