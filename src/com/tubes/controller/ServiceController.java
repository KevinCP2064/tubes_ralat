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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class ServiceController implements Initializable {
    @FXML
    private TableView<Service> serviceView;
    @FXML
    private TableColumn<Kendaraan,String> colPlat;
    @FXML
    private TableColumn<User,String> colPemilik;
    @FXML
    private TableColumn<Service, Date> colTanggal;
    @FXML
    private TableColumn<Service,String> colKeluhan;
    @FXML
    private TableColumn<Service,Double> colBiaya;

    private MainController mainController;
    private ObservableList<Service> services;
    private ServiceDaoImpl serviceDao;

    private ObservableList<Kendaraan> kendaraans;
    private KendaraanDaoImpl kendaraanDao;

    private ObservableList<User> users;
    private UserDaoImpl userDao;


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        serviceDao=new ServiceDaoImpl();
        kendaraanDao=new KendaraanDaoImpl();
        userDao=new UserDaoImpl();
        services= FXCollections.observableArrayList();
        kendaraans=FXCollections.observableArrayList();
        users=FXCollections.observableArrayList();
        try {
            services.addAll(serviceDao.fetchAll());
            kendaraans.addAll(kendaraanDao.fetchAll());
            users.addAll(userDao.fetchAll());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        serviceView.setItems(services);

        colPlat.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getNo_plat()));
        colPemilik.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getNama()));
        colTanggal.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getTanggal()));
        colKeluhan.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getKeterangan()));
        colBiaya.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getHarga()));
    }

    @FXML
    private void viewHistoryServiceUser(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("view/HistoryReparationUserForm.fxml"));
        Stage homeStage = new Stage();
        homeStage.setTitle("History Service User");
        homeStage.setScene(new Scene(root));
        homeStage.show();
    }

    @FXML
    private void printHistoryServiceUser(ActionEvent actionEvent) {
        //        JasperPrint jp;
//        Map param=new HashMap();
//        try{
//            jp= JasperFillManager.fillReport("report/ReportUser.jasper",param, MySQLConnection.createConnection());
//            JasperViewer viewer=new JasperViewer(jp,false);
//            viewer.setTitle("Report Servis User");
//            viewer.setVisible(true);
//        }catch(JRException e){
//            System.out.println(e);
//        }
    }
}
