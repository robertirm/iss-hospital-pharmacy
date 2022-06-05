package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Medicine;
import services.Services;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class MedicineMenuController {
    public Services services;
    ObservableList<Medicine> medicineObservableList = FXCollections.observableArrayList();

    public void setServices(Services services) {
        this.services = services;
        loadMedicineList();
    }

    @FXML ListView<Medicine> medicineListView;
    @FXML TextField nameTextField;
    @FXML TextField descriptionTextField;

    @FXML
    void backAction(ActionEvent event) throws IOException {
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

    void loadMedicineList() {
        medicineObservableList.setAll(services.getAllMedicine());
        medicineListView.setItems(medicineObservableList);
        medicineListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            nameTextField.setText(newValue.getName());
            descriptionTextField.setText(newValue.getDescription());
        });
    }

    @FXML
    void addMedicineAction(ActionEvent event) {
        String name = nameTextField.getText();
        String description = descriptionTextField.getText();

        Medicine medicine = new Medicine(name,description);

        services.addMedicine(medicine);
        medicineObservableList.setAll(services.getAllMedicine());
    }

    @FXML
    void deleteMedicineAction(ActionEvent event) {
        int id = medicineListView.getSelectionModel().getSelectedItem().getId();

        services.deleteMedicine(id);
        medicineObservableList.setAll(services.getAllMedicine());

        nameTextField.clear();
        descriptionTextField.clear();
    }

    @FXML
    void updateMedicineAction(ActionEvent event) {
        Medicine medicine = medicineListView.getSelectionModel().getSelectedItem();
        medicine.setName(nameTextField.getText());
        medicine.setDescription(descriptionTextField.getText());

        this.services.updateMedicine(medicine);
        loadMedicineList();
    }

}
