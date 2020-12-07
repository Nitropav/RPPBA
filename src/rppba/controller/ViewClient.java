package rppba.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import rppba.systems.ClientInstance;
import rppba.systems.LoadScene;

import java.util.ArrayList;

public class ViewClient {
    @FXML
    private TextField search;
    @FXML
    private TableView<InformationClient> ordersTable;
    @FXML
    private TableColumn<InformationClient, String> numberColumn;
    @FXML
    private TableColumn<InformationClient, Integer> nameColumn;
    @FXML
    private TableColumn<InformationClient, String> surnameColumn;
    @FXML
    private TableColumn<InformationClient, String> numberTelColumn;
    @FXML
    private TableColumn<InformationClient, String> emailColumn;
    @FXML
    private TableColumn<InformationClient, String> loyaltyColumn;
    @FXML
    private TableColumn<InformationClient, String> boundaryColumn;

    public void initialize() {
        fillTableView();
        search.textProperty().addListener(( observable, oldValue,  newValue)->
                filterPostavshikList(oldValue, newValue));
    }

    public void filterPostavshikList(String oldValue, String newValue) {
        ObservableList<InformationClient> filteredList = FXCollections.observableArrayList();
        if (search == null || (newValue.length() < oldValue.length() || newValue == null)) {
            fillTableView();
        } else {
            newValue = newValue.toUpperCase();
            for (InformationClient informationOrder : ordersTable.getItems()) {
                String filterPostavshik = informationOrder.getOrderNumber();
                if (filterPostavshik.toUpperCase().contains(newValue) || filterPostavshik.toUpperCase().contains(newValue)) {
                    filteredList.add(informationOrder);
                }
            }
            ordersTable.setItems(filteredList);
        }
    }

    private void fillTableView() {
        ClientInstance.INSTANCE.getInstance().send("getclientinfa " + search.getText());
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<InformationClient> informationClients = FXCollections.observableArrayList();
        String[] orderString;
        for (int i = 0; i < list.size(); i++) {
            orderString = list.get(i).split(" ", 7);
            InformationClient informationClient = new InformationClient();
            informationClient.setOrderNumber(orderString[0]);
            informationClient.setNameClient(orderString[1]);
            informationClient.setSurname(orderString[2]);
            informationClient.setNumberTel(orderString[3]);
            informationClient.setEmail(orderString[4]);
            informationClient.setViewLoylty(orderString[5]);
            informationClient.setBoundary((int)((1 - Double.parseDouble(orderString[6])) * 100));
            informationClients.add(informationClient);
        }
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameClient"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        numberTelColumn.setCellValueFactory(new PropertyValueFactory<>("numberTel"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        loyaltyColumn.setCellValueFactory(new PropertyValueFactory<>("viewLoylty"));
        boundaryColumn.setCellValueFactory(new PropertyValueFactory<>("boundary"));
        ordersTable.setItems(informationClients);
    }

    public void goBack() {
        ordersTable.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("menuManager", "Меню менеджера");
    }
}
