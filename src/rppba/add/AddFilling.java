package rppba.add;

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

public class AddFilling {

    @FXML
    private Label name;
    @FXML
    private ChoiceBox<String> modelChoiceBox;
    @FXML
    private TextField countProduct;

    private ArrayList<String> modelList;
    private ObservableList<String> models;
    private String idProduct = "";

    public void initialize() {
        name.setText(LogIn.getManager());
        ClientInstance.INSTANCE.getInstance().send("getmodel");
        modelList = ClientInstance.INSTANCE.getInstance().receiveResultList();
        models = FXCollections.observableArrayList(modelList);
        modelChoiceBox.setItems(models);
    }

    public void addFilling(){
        ClientInstance.INSTANCE.getInstance().send("getidproduct " + modelChoiceBox.getValue());
        idProduct = ClientInstance.INSTANCE.getInstance().receiveResultString();
        if (countProduct.getText().equals("")){
            ChangingFields.display("Заполните все поля!");
        }
        ClientInstance.INSTANCE.getInstance().send("addfilling " + idProduct + " "
                + countProduct.getText());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ChangingFields.display("Данные добавлены успешно!");
        } else {
            ChangingFields.display("Ошибка добавления данных!");
        }
    }

    public void addDate(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("adddate", "Add date");
    }

    public void goBack() {
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("menuManager", "Manager");
    }
}
