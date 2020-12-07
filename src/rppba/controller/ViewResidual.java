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

public class ViewResidual {
    @FXML
    private TextField search;
    @FXML
    private TableView<InformationResidual> ordersTable;
    @FXML
    private TableColumn<InformationResidual, String> modelColumn;
    @FXML
    private TableColumn<InformationResidual, Integer> residualColumn;

    public void initialize() {
        fillTableView();
        search.textProperty().addListener(( observable, oldValue,  newValue)->
                filterPostavshikList(oldValue, newValue));
    }

    public void filterPostavshikList(String oldValue, String newValue) {
        ObservableList<InformationResidual> filteredList = FXCollections.observableArrayList();
        if (search == null || (newValue.length() < oldValue.length() || newValue == null)) {
            fillTableView();
        } else {
            newValue = newValue.toUpperCase();
            for (InformationResidual informationResidual : ordersTable.getItems()) {
                String filterPostavshik = informationResidual.getModel();
                if (filterPostavshik.toUpperCase().contains(newValue) || filterPostavshik.toUpperCase().contains(newValue)) {
                    filteredList.add(informationResidual);
                }
            }
            ordersTable.setItems(filteredList);
        }
    }

    private void fillTableView() {
        ClientInstance.INSTANCE.getInstance().send("getresidualinfa " + search.getText());
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<InformationResidual> informationOrders = FXCollections.observableArrayList();
        String[] orderString;
        for (int i = 0; i < list.size(); i++) {
            orderString = list.get(i).split(" ", 2);
            InformationResidual informationResidual = new InformationResidual();
            informationResidual.setModel(orderString[0]);
            informationResidual.setResidual(orderString[1]);
            informationOrders.add(informationResidual);
        }
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        residualColumn.setCellValueFactory(new PropertyValueFactory<>("residual"));
        ordersTable.setItems(informationOrders);
    }

    public void goBack() {
        ordersTable.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("menuManager", "Меню менеджера");
    }
}
