package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Pharmacist;
import services.Services;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class LoginController {
    public Services services;

    public void setServices(Services services) {
        this.services = services;
    }

    @FXML
    TextField usernameTextField;
    @FXML
    TextField passwordTextField;

    @FXML
    void loginAction(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        Pharmacist pharmacist = services.loginPharmacist(username,password);

        if(pharmacist != null){
            switchToPharmacistMenu(event);
        }

    }

    @FXML
    public void switchToPharmacistMenu(ActionEvent event) throws IOException {
        URL url = Paths.get("./src/main/resources/pharmacist-menu.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);

        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        PharmacistMenuController controller = loader.getController();
        controller.setServices(services);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}