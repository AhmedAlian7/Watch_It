package com.example.loginpagedemo;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import watchIt.User;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button btnSignIn;

    @FXML
    private CheckBox cbShowPassword;

    @FXML
    private Hyperlink hlSignUp;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    private TextField tfPasswword;



    public void btnSignIn_Clicked(ActionEvent e) throws IOException {

        String Username = tfUsername.getText();
        String Password = pfPassword.getText();

        if (tfUsername.getText().isEmpty() || pfPassword.getText().isEmpty()) {
            MessageBox.showError("Error","Enter your username and password");
            return;
        }

        User user;
        try {
            user = User.Find(Username, Password);
        }
        catch (Exception ex) {
            user = null;
        }

        if (user != null) {
            MessageBox.showConfirmation( "Success","Login Successful!");
        }
        else {
            MessageBox.showError( "Failed","Wrong username or password !");
            return;
        }

        Global.CurrentUser = user;

        Parent root = FXMLLoader.load(getClass().getResource("Subscription.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void CbShowPassword_Clicked(ActionEvent e)
    {
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
    void hlSignIn_Clicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
