package rppba.menu;

import authorized.LogIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rppba.systems.ChangingFields;
import rppba.systems.ClientInstance;
import rppba.systems.LoadScene;
import java.util.ArrayList;

public class OrderMenu {
    @FXML
    private Label name;
    @FXML
    private ChoiceBox<String> lastNameChoiceBox;
    @FXML
    private TextField uslovie;
    @FXML
    private TextField address;
    @FXML
    private ChoiceBox<String> deliveryChoiceBox;
    @FXML
    private ChoiceBox<String> statesChoiceBox;

    private ArrayList<String> lastNameList;
    private ObservableList<String> lastNames;
    private ArrayList<String> deliveryList;
    private ObservableList<String> delivers;
    private ArrayList<String> statesList;
    private ObservableList<String> states;
    private String idClient = "";
    private String idDelivery = "";
    private String idStates = "";

    public void initialize() {
        name.setText(LogIn.getManager());
        ClientInstance.INSTANCE.getInstance().send("getlastnameclient");
        lastNameList = ClientInstance.INSTANCE.getInstance().receiveResultList();
        lastNames = FXCollections.observableArrayList(lastNameList);
        lastNameChoiceBox.setItems(lastNames);
        ClientInstance.INSTANCE.getInstance().send("getdelivery");
        deliveryList = ClientInstance.INSTANCE.getInstance().receiveResultList();
        delivers = FXCollections.observableArrayList(deliveryList);
        deliveryChoiceBox.setItems(delivers);
        ClientInstance.INSTANCE.getInstance().send("getstates");
        statesList = ClientInstance.INSTANCE.getInstance().receiveResultList();
        states = FXCollections.observableList(statesList);
        statesChoiceBox.setItems(states);
    }

    public void addNewClient(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("addnewclient", "Add new client");
    }

    public void addFilling(){
        ClientInstance.INSTANCE.getInstance().send("getidclient " + lastNameChoiceBox.getValue());
        idClient = ClientInstance.INSTANCE.getInstance().receiveResultString();
        ClientInstance.INSTANCE.getInstance().send("getiddelivery " + deliveryChoiceBox.getValue());
        idDelivery = ClientInstance.INSTANCE.getInstance().receiveResultString();
        ClientInstance.INSTANCE.getInstance().send("getidstates " + statesChoiceBox.getValue());
        idStates = ClientInstance.INSTANCE.getInstance().receiveResultString();
        if (uslovie.getText().equals("") || address.getText().equals("")){
            ChangingFields.display("Заполните все поля!");
        }
        ClientInstance.INSTANCE.getInstance().send("addoredersch1 " + address.getText() + " "
                + uslovie.getText() + " " + idClient + " " + idDelivery
                + " " + idStates);
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ChangingFields.display("Данные добавлены успешно!");
        } else {
            ChangingFields.display("Ошибка добавления данных!");
        }
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("addfilling", "Add filling");
    }

    public void viewOrders(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("viewordersidclient", "View orders");
    }

    public void goBack() {
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("menuManager", "Manager");
    }
}
