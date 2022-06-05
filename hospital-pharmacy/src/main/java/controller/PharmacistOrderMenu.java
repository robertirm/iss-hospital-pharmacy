package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Medicine;
import model.Order;
import services.Services;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;

public class PharmacistOrderMenu {
    public Services services;

    public void setServices(Services services) {
        this.services = services;
        loadData();
    }

    @FXML
    Button backButton;
    @FXML
    Button confirmOrderButton;
    @FXML
    Button declineOrderButton;
    @FXML
    ListView<Order> ordersListView;
    @FXML
    ListView<Medicine> itemsListView;
    @FXML
    TextArea mentionTextField;

    ObservableList<Order> ordersObservableList = FXCollections.observableArrayList();
    ObservableList<Medicine> itemsObservableList = FXCollections.observableArrayList();

    void loadData() {
        ordersObservableList.clear();
        itemsObservableList.clear();

        ordersObservableList.setAll(services.getAllOrders().stream().filter(x-> Objects.equals(x.getStatus(), "sent")).collect(Collectors.toList()));
        ordersListView.setItems(ordersObservableList);

        ordersListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null ){
                itemsObservableList.setAll(newValue.getMedicineList());
                itemsListView.setItems(itemsObservableList);
                mentionTextField.setText(newValue.getDescription());
            }
        });
    }

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

    @FXML
    void confirmOrderAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Order closed!");
        alert.showAndWait();
        Order order = ordersListView.getSelectionModel().getSelectedItem();
        order.setStatus("closed");

        this.services.updateOrder(order);
        loadData();
    }

    @FXML
    void declineOrderAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Order declined!");
        alert.showAndWait();
        Order order = ordersListView.getSelectionModel().getSelectedItem();
        order.setStatus("open");

        this.services.updateOrder(order);
        loadData();
    }

}
