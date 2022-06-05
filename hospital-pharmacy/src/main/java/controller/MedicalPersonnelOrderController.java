package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Medicine;
import model.Order;
import services.Services;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MedicalPersonnelOrderController {
    private Services services;

    public void setServices(Services services) {
        this.services = services;
        loadOrderList();
    }

    ObservableList<Order> ordersObservableList = FXCollections.observableArrayList();
    ObservableList<Medicine> currentMedicineObservableList = FXCollections.observableArrayList();
    ObservableList<Medicine> allMedicineObservableList = FXCollections.observableArrayList();

    @FXML
    ListView<Order> ordersListView;
    @FXML
    ListView<Medicine> allMedicineListView;
    @FXML
    ListView<Medicine> currentMedicineListView;

    @FXML
    Button createButton;
    @FXML
    Button updateButton;
    @FXML
    Button deleteButton;
    @FXML
    Button sendButton;
    @FXML
    Button clearButton;
    @FXML
    TextField descriptionTextField;

    void loadOrderList() {
        currentMedicineObservableList.clear();

        ordersObservableList.setAll(services.getAllOrders().stream().filter(x-> Objects.equals(x.getStatus(), "open")).collect(Collectors.toList()));
        ordersListView.setItems(ordersObservableList);
        ordersListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null ){
                currentMedicineObservableList.setAll(newValue.getMedicineList());
                currentMedicineListView.setItems(currentMedicineObservableList);
                descriptionTextField.setText(newValue.getDescription());
            }

        });
        allMedicineObservableList.setAll(services.getAllMedicine());
        allMedicineListView.setItems(allMedicineObservableList);
        allMedicineListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            currentMedicineObservableList.add(newValue);
        });
    }

    @FXML
    void addButtonAction() {
        String description = descriptionTextField.getText();
        Order order = new Order(description, "open");
        List<Medicine> medicineList = new ArrayList<>(currentMedicineObservableList);
        order.setMedicineList(medicineList);

        this.services.addOrder(order);
        ordersObservableList.add(order);
    }

    @FXML
    void deleteButtonAction() {
        Order order = ordersListView.getSelectionModel().getSelectedItem();
        this.services.deleteOrder(order.getId());
        ordersObservableList.remove(order);
    }

    @FXML
    void updateButtonAction() {
        String description = descriptionTextField.getText();
        Order order = ordersListView.getSelectionModel().getSelectedItem();
        List<Medicine> medicineList = new ArrayList<>(currentMedicineObservableList);
        order.setMedicineList(medicineList);
        order.setDescription(description);

        this.services.updateOrder(order);
        loadOrderList();
    }

    @FXML
    void sendButtonAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Order sent!");
        alert.showAndWait();
        Order order = ordersListView.getSelectionModel().getSelectedItem();
        order.setStatus("sent");

        this.services.updateOrder(order);
        loadOrderList();
    }

    @FXML
    void clearButtonAction() {
        currentMedicineObservableList.clear();
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
