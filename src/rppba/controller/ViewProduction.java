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

public class ViewProduction {
    @FXML
    private TextField search;
    @FXML
    private TableView<InformationProduction> ordersTable;
    @FXML
    private TableColumn<InformationProduction, String> idColumn;
    @FXML
    private TableColumn<InformationProduction, String> modelColumn;
    @FXML
    private TableColumn<InformationProduction, Integer> residualColumn;

    public void initialize() {
        fillTableView();
        search.textProperty().addListener(( observable, oldValue,  newValue)->
                filterPostavshikList(oldValue, newValue));
    }

    public void filterPostavshikList(String oldValue, String newValue) {
        ObservableList<InformationProduction> filteredList = FXCollections.observableArrayList();
        if (search == null || (newValue.length() < oldValue.length() || newValue == null)) {
            fillTableView();
        } else {
            newValue = newValue.toUpperCase();
            for (InformationProduction informationResidual : ordersTable.getItems()) {
                String filterPostavshik = informationResidual.getIdProduction();
                if (filterPostavshik.toUpperCase().contains(newValue) || filterPostavshik.toUpperCase().contains(newValue)) {
                    filteredList.add(informationResidual);
                }
            }
            ordersTable.setItems(filteredList);
        }
    }

    private void fillTableView() {
        ClientInstance.INSTANCE.getInstance().send("getproductioninfa " + search.getText());
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<InformationProduction> informationProductions = FXCollections.observableArrayList();
        String[] orderString;
        for (int i = 0; i < list.size(); i++) {
            orderString = list.get(i).split(" ", 3);
            InformationProduction informationProduction = new InformationProduction();
            informationProduction.setIdProduction(orderString[0]);
            informationProduction.setModel(orderString[1]);
            informationProduction.setCount(orderString[2]);
            informationProductions.add(informationProduction);
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idProduction"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        residualColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        ordersTable.setItems(informationProductions);
    }

    public void goBack() {
        ordersTable.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("viewordersidclient", "Меню просмотра заказов");
    }
}
