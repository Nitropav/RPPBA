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

public class ViewProduct {
    @FXML
    private TextField search;
    @FXML
    private TableView<InformationProduct> ordersTable;
    @FXML
    private TableColumn<InformationProduct, String> numberColumn;
    @FXML
    private TableColumn<InformationProduct, Integer> modelColumn;
    @FXML
    private TableColumn<InformationProduct, String> priceColumn;
    @FXML
    private TableColumn<InformationProduct, String> typColumn;
    @FXML
    private TableColumn<InformationProduct, String> shellColumn;
    @FXML
    private TableColumn<InformationProduct, String> kernelColumn;

    public void initialize() {
        fillTableView();
        search.textProperty().addListener(( observable, oldValue,  newValue)->
                filterPostavshikList(oldValue, newValue));
    }

    public void filterPostavshikList(String oldValue, String newValue) {
        ObservableList<InformationProduct> filteredList = FXCollections.observableArrayList();
        if (search == null || (newValue.length() < oldValue.length() || newValue == null)) {
            fillTableView();
        } else {
            newValue = newValue.toUpperCase();
            for (InformationProduct informationProduct : ordersTable.getItems()) {
                String filterPostavshik = informationProduct.getNumberProduct();
                if (filterPostavshik.toUpperCase().contains(newValue) || filterPostavshik.toUpperCase().contains(newValue)) {
                    filteredList.add(informationProduct);
                }
            }
            ordersTable.setItems(filteredList);
        }
    }

    private void fillTableView() {
        ClientInstance.INSTANCE.getInstance().send("getproductall " + search.getText());
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<InformationProduct> informationProducts = FXCollections.observableArrayList();
        String[] orderString;
        for (int i = 0; i < list.size(); i++) {
            orderString = list.get(i).split(" ", 6);
            InformationProduct informationProduct = new InformationProduct();
            informationProduct.setNumberProduct(orderString[0]);
            informationProduct.setModelProduct(orderString[1]);
            informationProduct.setPriceProduct(orderString[2]);
            informationProduct.setTypProduct(orderString[3]);
            informationProduct.setShellProduct(orderString[4]);
            informationProduct.setKerelProduct(orderString[5]);
            informationProducts.add(informationProduct);
        }
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("numberProduct"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("modelProduct"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("priceProduct"));
        typColumn.setCellValueFactory(new PropertyValueFactory<>("typProduct"));
        shellColumn.setCellValueFactory(new PropertyValueFactory<>("shellProduct"));
        kernelColumn.setCellValueFactory(new PropertyValueFactory<>("kerelProduct"));
        ordersTable.setItems(informationProducts);
    }

    public void goBack() {
        ordersTable.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("menuManager", "Меню менеджера");
    }
}
