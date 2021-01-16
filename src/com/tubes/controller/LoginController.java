package com.tubes.controller;

import com.tubes.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;

    @FXML
    private void loginActionBtn(ActionEvent actionEvent) throws IOException {
        if(txtUsername.getText().equals("admin") && txtPassword.getText().equals("b")) {
            Parent root = FXMLLoader.load(Main.class.getResource("view/HomePage.fxml"));
            Stage homeStage = new Stage();
            homeStage.setTitle("Home");
            homeStage.setScene(new Scene(root));
            homeStage.show();
        }else{
            Parent root = FXMLLoader.load(Main.class.getResource("view/HomePageUser.fxml"));
            Stage homeStage = new Stage();
            homeStage.setTitle("Home User");
            homeStage.setScene(new Scene(root));
            homeStage.show();
        }
    }
}
