package com.example.loginpagedemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class SubscriptionController {

    @FXML
    private Button btnBasic;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnPremium;

    @FXML
    private Button btnStandard;

    @FXML
    private Label lblBasicPrice;

    @FXML
    private Label lblPremiumPrice;

    @FXML
    private Label lblStandardPrice;

    @FXML
    private AnchorPane paneBasic;

    @FXML
    private AnchorPane panePremium;

    @FXML
    private AnchorPane paneStandard;

    @FXML
    void btnBasic_Clicked(ActionEvent event) {
        btnPremium.setStyle("-fx-background-color: white;");
        btnStandard.setStyle("-fx-background-color: white;");
        btnBasic.setStyle("-fx-background-color: #dca523;");
        paneBasic.setVisible(true);
        paneStandard.setVisible(false);
        panePremium.setVisible(false);
    }

    @FXML
    void btnNext_Clicked(ActionEvent event) {

    }

    @FXML
    void btnPremium_Clicked(ActionEvent event) {
        btnBasic.setStyle("-fx-background-color: white;");
        btnStandard.setStyle("-fx-background-color: white;");
        btnPremium.setStyle("-fx-background-color: #dca523;");
        paneBasic.setVisible(false);
        paneStandard.setVisible(false);
        panePremium.setVisible(true);
    }

    @FXML
    void btnStandard_Clicked(ActionEvent event) {
        btnBasic.setStyle("-fx-background-color: white;");
        btnPremium.setStyle("-fx-background-color: white;");
        btnStandard.setStyle("-fx-background-color: #dca523;");
        paneBasic.setVisible(false);
        paneStandard.setVisible(true);
        panePremium.setVisible(false);
    }

}
