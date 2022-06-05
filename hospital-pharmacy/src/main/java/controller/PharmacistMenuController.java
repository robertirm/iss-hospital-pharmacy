package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.Services;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class PharmacistMenuController {
    public Services services;

    public void setServices(Services services) {
        this.services = services;
    }

    @FXML
    public void medicineMenuAction(ActionEvent event) throws IOException {
        URL url = Paths.get("./src/main/resources/medicine-menu.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);

        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        MedicineMenuController controller = loader.getController();
        controller.setServices(services);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void orderMenuAction(ActionEvent event) throws IOException {
        URL url = Paths.get("./src/main/resources/pharmacist-order-menu.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);

        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        PharmacistOrderMenu controller = loader.getController();
        controller.setServices(services);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void backButtonAction(ActionEvent event) throws IOException {
        URL url = Paths.get("./src/main/resources/login.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);

        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        LoginController controller = loader.getController();
        controller.setServices(services);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
