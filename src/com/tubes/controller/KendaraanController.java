package com.tubes.controller;

import com.tubes.dao.KendaraanDaoImpl;
import com.tubes.dao.UserDaoImpl;
import com.tubes.entity.Kendaraan;
import com.tubes.entity.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class KendaraanController implements Initializable {
    @FXML
    private TextField txtNoPlat;
    @FXML
    private TextField txtAlamat;
    @FXML
    private ComboBox<User> comboUser;
    @FXML
    private TextField txtMerek;
    @FXML
    private TextField txtWarna;
    @FXML
    private TextField txtKeluhan;
    @FXML
    private TextField txtTahunBuat;
    @FXML
    private TableView<Kendaraan> KendaraanView;
    @FXML
    private TableColumn<Kendaraan,String> colPlat;
    @FXML
    private TableColumn<Kendaraan,String> colPem;
    @FXML
    private TableColumn<Kendaraan,String> colAlamat;

    private MainController mainController;
    private KendaraanDaoImpl kendaraanDao;
    private ObservableList<Kendaraan> kendaraans;


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void saveMobilAction(ActionEvent actionEvent) {
        if(txtNoPlat.getText().trim().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Tolong isi field yang kosong");
            alert.showAndWait();
        }else
        {
            Kendaraan kendaraan=new Kendaraan();
            kendaraan.setNo_plat(txtNoPlat.getText().trim());
            kendaraan.setWarna(txtWarna.getText().trim());
            kendaraan.setMerek(txtMerek.getText().trim());
            kendaraan.setAlamat(txtAlamat.getText().trim());
            kendaraan.setTahun_buat(txtTahunBuat.getText().trim());
            kendaraan.setUser(comboUser.getValue());
            try {
                if(mainController.getKendaraanDao().addData(kendaraan) == 1){
                    mainController.getKendaraans().clear();
                    mainController.getKendaraans().addAll(mainController.getKendaraanDao().fetchAll());
                    txtNoPlat.clear();
                    txtWarna.clear();
                    txtMerek.clear();
                    txtAlamat.clear();
                    txtTahunBuat.clear();
                    comboUser.setValue(null);
                }
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @FXML
    private void deleteMobilAction(ActionEvent actionEvent) {
        Kendaraan kendaraan;
        kendaraan=KendaraanView.getSelectionModel().getSelectedItem();
        try {
            if(mainController.getKendaraanDao().deleteData(kendaraan)==1){
                mainController.getKendaraans().clear();
                mainController.getKendaraans().addAll(mainController.getKendaraanDao().fetchAll());
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void updateMobilAction(ActionEvent actionEvent) {
        Kendaraan kendaraan;
        kendaraan=KendaraanView.getSelectionModel().getSelectedItem();
        kendaraan.setNo_plat(txtNoPlat.getText().trim());
        kendaraan.setWarna(txtWarna.getText().trim());
        kendaraan.setMerek(txtMerek.getText().trim());
        kendaraan.setAlamat(txtAlamat.getText().trim());
        kendaraan.setTahun_buat(txtTahunBuat.getText().trim());
        kendaraan.setUser(comboUser.getValue());

        try {
            if(mainController.getKendaraanDao().updateData(kendaraan)==1){
                mainController.getKendaraans().clear();
                mainController.getKendaraans().addAll(mainController.getKendaraanDao().fetchAll());
                txtNoPlat.clear();
                txtWarna.clear();
                txtMerek.clear();
                txtAlamat.clear();
                txtTahunBuat.clear();
                comboUser.setValue(null);
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kendaraanDao=new KendaraanDaoImpl();
        kendaraans= FXCollections.observableArrayList();
        try {
            kendaraans.addAll(kendaraanDao.fetchAll());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        KendaraanView.setItems(kendaraans);

        colPlat.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getNo_plat()));
        colPem.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getUser().getNama()));
        colAlamat.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getAlamat()));

    }
}
