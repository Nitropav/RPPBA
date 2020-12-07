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

public class ControlLoyalty {
    @FXML
    private TextField search;
    @FXML
    private TableView<InformationLoyalty> ordersTable;
    @FXML
    private TableColumn<InformationLoyalty, String> numberColumn;
    @FXML
    private TableColumn<InformationLoyalty, String> nameColumn;
    @FXML
    private TableColumn<InformationLoyalty, String> saleColumn;
    @FXML
    private TableColumn<InformationLoyalty, String> boundaryTelColumn;
    @FXML
    private TextField numberloyalty;
    @FXML
    private TextField saleloyalty;
    @FXML
    private TextField boundaryloyalty;

    public void initialize() {
        fillTableView();
        search.textProperty().addListener(( observable, oldValue,  newValue)->
                filterPostavshikList(oldValue, newValue));
    }

    public void filterPostavshikList(String oldValue, String newValue) {
        ObservableList<InformationLoyalty> filteredList = FXCollections.observableArrayList();
        if (search == null || (newValue.length() < oldValue.length() || newValue == null)) {
            fillTableView();
        } else {
            newValue = newValue.toUpperCase();
            for (InformationLoyalty informationLoyalty : ordersTable.getItems()) {
                String filterPostavshik = informationLoyalty.getNumberLoyalty();
                if (filterPostavshik.toUpperCase().contains(newValue) || filterPostavshik.toUpperCase().contains(newValue)) {
                    filteredList.add(informationLoyalty);
                }
            }
            ordersTable.setItems(filteredList);
        }
    }

    private void fillTableView() {
        ClientInstance.INSTANCE.getInstance().send("getloyaltyall " + search.getText());
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<InformationLoyalty> informationLoyalties = FXCollections.observableArrayList();
        String[] orderString;
        for (int i = 0; i < list.size(); i++) {
            orderString = list.get(i).split(" ", 4);
            InformationLoyalty informationLoyalty = new InformationLoyalty();
            informationLoyalty.setNumberLoyalty(orderString[0]);
            informationLoyalty.setNameLoyalty(orderString[1]);
            informationLoyalty.setSaleLoyalty((int)((1 - Double.parseDouble(orderString[2])) * 100));
            informationLoyalty.setBoundaryLoyalty(orderString[3]);
            informationLoyalties.add(informationLoyalty);
        }
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("numberLoyalty"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameLoyalty"));
        saleColumn.setCellValueFactory(new PropertyValueFactory<>("saleLoyalty"));
        boundaryTelColumn.setCellValueFactory(new PropertyValueFactory<>("boundaryLoyalty"));
        ordersTable.setItems(informationLoyalties);
    }

    public void updateLoyalty(){
        if (numberloyalty.getText().equals("") || saleloyalty.getText().equals("") || boundaryloyalty.getText().equals("")) {
            ChangingFields.display("Заполните все поля!");
            return;
        }
        double pr = ((100 - Double.parseDouble(saleloyalty.getText())) / 100);
        ClientInstance.INSTANCE.getInstance().send("updateloyalty " + numberloyalty.getText() + " " + pr + " " +
                boundaryloyalty.getText());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ChangingFields.display("Данные обновлены успешно!");
        } else {
            ChangingFields.display("Ошибка обновления данных");
        }
    }

    public void goBack() {
        ordersTable.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("menuManager", "Меню менеджера");
    }
}
