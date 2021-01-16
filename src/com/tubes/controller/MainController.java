package com.tubes.controller;

import com.tubes.Main;
import com.tubes.dao.KendaraanDaoImpl;
import com.tubes.dao.ServiceDaoImpl;
import com.tubes.dao.UserDaoImpl;
import com.tubes.entity.Kendaraan;
import com.tubes.entity.Service;
import com.tubes.entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private AnchorPane homeRoot;

    private ObservableList<User> users;
    private ObservableList<Kendaraan> kendaraans;
    private ObservableList<Service> services;
    private UserDaoImpl userDao;
    private KendaraanDaoImpl kendaraanDao;
    private ServiceDaoImpl serviceDao;

    public ObservableList<User> getUsers() {
        if (users == null) {
            users = FXCollections.observableArrayList();
            users.addAll(getUserDao().showAllData());
        }
        return users;
    }

    public ObservableList<Kendaraan> getKendaraans() {
        if (kendaraans == null) {
            kendaraans = FXCollections.observableArrayList();
            kendaraans.addAll(getKendaraanDao().showAllData());
        }
        return kendaraans;
    }

    public ObservableList<Service> getServices() {
        if (services == null) {
            services = FXCollections.observableArrayList();
            services.addAll(getServiceDao().showAllData());
        }
        return services;
    }

    public UserDaoImpl getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    public KendaraanDaoImpl getKendaraanDao() {
        if (kendaraanDao == null) {
            kendaraanDao = new KendaraanDaoImpl();
        }
        return kendaraanDao;
    }

    public ServiceDaoImpl getServiceDao() {
        if (serviceDao == null) {
            serviceDao = new ServiceDaoImpl();
        }
        return serviceDao;
    }

    @FXML
    private void openKendaraanView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/KendaraanForm.fxml"));
        AnchorPane root = loader.load();
        KendaraanController kendaraanController = loader.getController();
        kendaraanController.setMainController(this);
        Scene scene = new Scene(root);
        Stage kendaraanStage = new Stage();
        kendaraanStage.setTitle("Kendaraan Form");
        kendaraanStage.setScene(scene);
        kendaraanStage.initOwner(homeRoot.getScene().getWindow());
        kendaraanStage.show();
    }

    @FXML
    private void openUserView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/UserForm.fxml"));
        AnchorPane root = loader.load();
        UserController userController = loader.getController();
        userController.setMainController(this);
        Scene scene = new Scene(root);
        Stage userStage = new Stage();
        userStage.setTitle("User Form");
        userStage.setScene(scene);
        userStage.initOwner(homeRoot.getScene().getWindow());
        userStage.show();
    }

    @FXML
    private void openHistoryPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/HistoryReparationForm.fxml"));
        VBox root = loader.load();
        ServiceController serviceController = loader.getController();
        serviceController.setMainController(this);
        Scene scene = new Scene(root);
        Stage historyStage = new Stage();
        historyStage.setTitle("History Reparation Form");
        historyStage.setScene(scene);
        historyStage.initOwner(homeRoot.getScene().getWindow());
        historyStage.show();
    }

    @FXML
    private void printReport(ActionEvent actionEvent) {
//        JasperPrint jp;
//        Map param=new HashMap();
//        try{
//            jp= JasperFillManager.fillReport("report/ReportServisAdmin.jasper",param, MySQLConnection.createConnection());
//            JasperViewer viewer=new JasperViewer(jp,false);
//            viewer.setTitle("Report Servis Admin");
//            viewer.setVisible(true);
//        }catch(JRException e){
//            System.out.println(e);
//        }
    }
}
