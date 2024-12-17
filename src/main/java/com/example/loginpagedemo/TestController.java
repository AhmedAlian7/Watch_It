package com.example.loginpagedemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent ;
import watchIt.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TestController implements Initializable {

    @FXML
    private TableView<User> UsersTable;

    @FXML
    private ContextMenu context_User;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<User> users;
        try {
            users = User.LoadUsersFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            // Convert ArrayList to ObservableList
            ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
            UsersTable.setItems(observableUsers);
        } catch (Exception e) {
            System.out.println("Error setting items in UsersTable: " + e.getMessage());
        }

    }

    public void Delete_Clicked(ActionEvent actionEvent) {

        UsersTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Get the value of the first cell
                int Selected_ID = newSelection.getID();

            }
        });
    }
}
