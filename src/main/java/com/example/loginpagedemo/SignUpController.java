package com.example.loginpagedemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import watchIt.Subscription;
import watchIt.User;

import java.io.IOException;

public class SignUpController {

    @FXML
    private Button btnSignIn;

    @FXML
    private CheckBox cbShowPassword;

    @FXML
    private Hyperlink hlSignIn;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfUsername;

    @FXML
    private TextField tfPasswword;

    @FXML
    void CbShowPassword_Clicked(ActionEvent event) {
        if (cbShowPassword.isSelected()) {
            tfPasswword.setText(pfPassword.getText());
            pfPassword.setVisible(false);
            tfPasswword.setVisible(true);
        }
        else {
            pfPassword.setText(tfPasswword.getText());
            tfPasswword.setVisible(false);
            pfPassword.setVisible(true);
        }
    }

    @FXML
    void btnSignIn_Clicked(ActionEvent event) throws IOException {

        String FirstName = tfFirstName.getText();
        String LastName = tfLastName.getText();
        String Email = tfEmail.getText();
        String Username = tfUsername.getText();
        String Password = pfPassword.getText();

        if (!isEmailValid(Email)) {
            MessageBox.showError("Error","Enter a valid email");
            return;
        }
        if (User.isUserExist(Username)){
            MessageBox.showError("Error","Username is already exists, please enter a new username");
            return;
        }
        User newUser = new User(Username, Subscription.enPlan.Non,Email,LastName,FirstName,Password);
       if (User.AddNewUser(newUser)) {
           MessageBox.showInfo("Confirm","Account Created Successfully");
       }
       else {
           MessageBox.showError("Error","An error occurred, Account creation failed");
       }

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml")); // Should load Scene1.fxml
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void hlSignIn_Clicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml")); // Should load Scene1.fxml
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private boolean isEmailValid(String email) {
        // Regex for email validation
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return email != null && email.matches(emailRegex);
    }

}
