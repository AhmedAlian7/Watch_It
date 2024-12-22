package com.example.loginpagedemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import watchIt.Actor;
import watchIt.Person;

import java.time.LocalDate;
import java.util.ArrayList;

public class AddActorController {

    @FXML
    private TextField AFname;

    @FXML
    private ChoiceBox<Person.enGender> AGender;

    @FXML
    private TextField ALname;

    @FXML
    private TextField ANation;

    @FXML
    private DatePicker ADOB;

    private ArrayList<Actor> actors;

    public void setActorsList(ArrayList<Actor> actors) {
        this.actors = actors; // Pass the actors list from AddMovieController
    }

    public void btn_SaveActor_Clicked(ActionEvent event) {
        if (AFname.getText().isEmpty() || ALname.getText().isEmpty() || ADOB.getValue() == null || AGender.getValue() == null || ANation.getText().isEmpty()) {
            MessageBox.showError("Error", "Please fill in all the actor details.");
            return;
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

        MessageBox.showConfirmation("Success", "Actor added successfully!");
    }
}
