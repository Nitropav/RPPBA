package rppba.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import rppba.systems.ChangingFields;
import rppba.systems.ClientInstance;
import rppba.systems.LoadScene;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ViewOrdersClient {
    @FXML
    private TextField search;
    @FXML
    private TableView<InformationOrder> ordersTable;
    @FXML
    private TableColumn<InformationOrder, String> numberColumn;
    @FXML
    private TableColumn<InformationOrder, Integer> priceColumn;
    @FXML
    private TableColumn<InformationOrder, String> addressColumn;
    @FXML
    private TableColumn<InformationOrder, String> clientColumn;
    @FXML
    private TableColumn<InformationOrder, String> deliveryColumn;
    @FXML
    private TableColumn<InformationOrder, String> statesColumn;
    @FXML
    private TableColumn<InformationOrder, String> dateColumn;

    public void initialize() {
        fillTableView();
        search.textProperty().addListener(( observable, oldValue,  newValue)->
                filterPostavshikList(oldValue, newValue));
    }

    public void filterPostavshikList(String oldValue, String newValue) {
        ObservableList<InformationOrder> filteredList = FXCollections.observableArrayList();
        if (search == null || (newValue.length() < oldValue.length() || newValue == null)) {
            fillTableView();
        } else {
            newValue = newValue.toUpperCase();
            for (InformationOrder informationOrder : ordersTable.getItems()) {
                String filterPostavshik = informationOrder.getClientLast();
                if (filterPostavshik.toUpperCase().contains(newValue) || filterPostavshik.toUpperCase().contains(newValue)) {
                    filteredList.add(informationOrder);
                }
            }
            ordersTable.setItems(filteredList);
        }
    }

    private void fillTableView() {
        ClientInstance.INSTANCE.getInstance().send("getorderview " + search.getText());
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<InformationOrder> informationOrders = FXCollections.observableArrayList();
        String[] orderString;
        for (int i = 0; i < list.size(); i++) {
            orderString = list.get(i).split(" ", 7);
            InformationOrder informationOrder = new InformationOrder();
            informationOrder.setOrderNumber(orderString[0]);
            informationOrder.setPrice(orderString[1]);
            informationOrder.setAddress(orderString[2]);
            informationOrder.setClientLast(orderString[3]);
            informationOrder.setViewDelivery(orderString[4]);
            informationOrder.setViewStates(orderString[5]);
            informationOrder.setDate(orderString[6]);
            informationOrders.add(informationOrder);
        }
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("clientLast"));
        deliveryColumn.setCellValueFactory(new PropertyValueFactory<>("viewDelivery"));
        statesColumn.setCellValueFactory(new PropertyValueFactory<>("viewStates"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        ordersTable.setItems(informationOrders);
    }

    public void deleteOrder() {
        if (ordersTable.getSelectionModel().getSelectedItem() == null) {
            ChangingFields.display("Выберите заказ для удаления!");
        } else {
            ClientInstance.INSTANCE.getInstance().send("removeorder " +
                    ordersTable.getSelectionModel().getSelectedItem().getOrderNumber());
            if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
                fillTableView();
                ChangingFields.display("Удален!");
            } else {
                ChangingFields.display("Ошибка удаления!");
            }
        }
    }

    public void saveToFile() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить в файл");
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));
                for (InformationOrder informationOrder : ordersTable.getItems()) {
                    outWriter.write(informationOrder.toString());
                    outWriter.newLine();
                }
                outWriter.close();
            } catch (IOException e) {
                ChangingFields.display("Ошибка записи в файл!");
            }
        }
    }

    public void goBack() {
        ordersTable.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("ordersmenu", "Orders menu");
    }
}
