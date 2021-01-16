package com.tubes.controller;

import com.tubes.Main;
import com.tubes.dao.KendaraanDaoImpl;
import com.tubes.dao.ServiceDaoImpl;
import com.tubes.dao.UserDaoImpl;
import com.tubes.entity.Kendaraan;
import com.tubes.entity.Service;
import com.tubes.entity.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtDaftarNama;
    @FXML
    private TextField txtDaftarEmail;
    @FXML
    private TextField txtDaftarTelp;
    @FXML
    private TextField txtDaftarUsername;
    @FXML
    private TextField txtDaftarPassword;
    @FXML
    private TextField txtDaftarRePassword;
    
    @FXML
    private TableColumn<User,String> colNama;
    @FXML
    private TableColumn<User,String> colEmail;
    @FXML
    private TableColumn<User,String> colNoTelp;
    @FXML
    private TableColumn<User,String> colUsername;
    private MainController mainController;
    

    private UserDaoImpl userDao;
    private ObservableList<User> users;
    @FXML
    private TableView<User> userView;


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void saveUserAction(ActionEvent actionEvent) {
        if(txtDaftarNama.getText().trim().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Tolong isi field yang kosong");
            alert.showAndWait();
        }else if(txtDaftarPassword.getText().trim().equals(txtDaftarRePassword)) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Password dan Re-Password harus sama!");
            alert.showAndWait();
        }else
        {
            User user=new User();
            user.setNama(txtDaftarNama.getText().trim());
            user.setEmail(txtDaftarEmail.getText().trim());
            user.setTelpon(txtDaftarTelp.getText().trim());
            user.setUsername(txtDaftarUsername.getText().trim());
            user.setPassword(txtDaftarPassword.getText().trim());
            try {
                if(mainController.getUserDao().addData(user) == 1){
                    mainController.getUsers().clear();
                    mainController.getUsers().addAll(mainController.getUserDao().fetchAll());
                    txtDaftarNama.clear();
                    txtDaftarEmail.clear();
                    txtDaftarTelp.clear();
                    txtDaftarUsername.clear();
                    txtDaftarPassword.clear();
                    txtDaftarRePassword.clear();
                }
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @FXML
    private void deleteUserAction(ActionEvent actionEvent) {
        User user;
        user=userView.getSelectionModel().getSelectedItem();
        try {
            if(mainController.getUserDao().deleteData(user)==1){
                mainController.getUsers().clear();
                mainController.getUsers().addAll(mainController.getUserDao().fetchAll());
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void updateUserAction(ActionEvent actionEvent) {
        User user;
        user=userView.getSelectionModel().getSelectedItem();
        user.setNama(txtDaftarNama.getText());
        user.setEmail(txtDaftarEmail.getText());
        user.setTelpon(txtDaftarTelp.getText());
        user.setUsername(txtDaftarUsername.getText());
        user.setPassword(txtDaftarPassword.getText());
        try {
            if(mainController.getUserDao().updateData(user)==1){
                mainController.getUsers().clear();
                mainController.getUsers().addAll(mainController.getUserDao().fetchAll());
                txtDaftarNama.clear();
                txtDaftarEmail.clear();
                txtDaftarTelp.clear();
                txtDaftarUsername.clear();
                txtDaftarPassword.clear();
                txtDaftarRePassword.clear();
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDao = new UserDaoImpl();
        users = FXCollections.observableArrayList();
        try {
            users.addAll(userDao.fetchAll());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        userView.setItems(users);

        colNama.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getNama()));
        colEmail.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getEmail()));
        colNoTelp.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getTelpon()));
        colUsername.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getUsername()));

    }
}
