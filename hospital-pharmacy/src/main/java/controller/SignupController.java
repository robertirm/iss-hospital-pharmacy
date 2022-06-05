package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.MedicalPersonnel;
import model.Pharmacist;
import services.Services;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class SignupController {
    public Services services;

    public void setServices(Services services) {
        this.services = services;
    }

    @FXML
    TextField usernameTextField;
    @FXML
    TextField passwordTextField;


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

    @FXML
    void registerAsPharmacistAction(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        this.services.registerPharmacist(new Pharmacist(username,password));

        backButtonAction(event);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Registration successful, please login");
        alert.showAndWait();
    }

    @FXML
    void registerAsMedicalAction(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        this.services.registerMedicalPersonnel(new MedicalPersonnel(username,password));

        backButtonAction(event);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Registration successful, please login");
        alert.showAndWait();
    }

}
