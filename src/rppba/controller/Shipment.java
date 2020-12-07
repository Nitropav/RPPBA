package rppba.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import rppba.systems.ChangingFields;
import rppba.systems.ClientInstance;
import rppba.systems.LoadScene;

import java.util.ArrayList;

public class Shipment {
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
                String filterPostavshik = informationOrder.getOrderNumber();
                if (filterPostavshik.toUpperCase().contains(newValue) || filterPostavshik.toUpperCase().contains(newValue)) {
                    filteredList.add(informationOrder);
                }
            }
            ordersTable.setItems(filteredList);
        }
    }

    private void fillTableView() {
        ClientInstance.INSTANCE.getInstance().send("getshipOrders " + search.getText());
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

    public void shipmentOrder(){
        if (ordersTable.getSelectionModel().getSelectedItem() == null) {
            ChangingFields.display("Выберите заказ!");
        } else {
            ClientInstance.INSTANCE.getInstance().send("shiporders " + ordersTable.getSelectionModel().
                    getSelectedItem().getOrderNumber());
        }
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            fillTableView();
            ChangingFields.display("Отгружено!");
        } else {
            ChangingFields.display("Ошибка отгрузки!");
        }
    }

    public void goBack() {
        ordersTable.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("menuManager", "Меню менеджера");
    }
}
