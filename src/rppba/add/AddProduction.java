package rppba.add;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import rppba.systems.ChangingFields;
import rppba.systems.ClientInstance;
import rppba.systems.LoadScene;

import java.util.ArrayList;

public class AddProduction {
    @FXML
    private ChoiceBox<String> modelChoiceBox;
    @FXML
    private TextField count;
    @FXML
    private ChoiceBox<String> dateChoiceBox;

    private ArrayList<String> modelList;
    private ObservableList<String> models;
    private ArrayList<String> dateList;
    private ObservableList<String> dates;
    private String id = "";

    public void initialize() {
        ClientInstance.INSTANCE.getInstance().send("getmodel");
        modelList = ClientInstance.INSTANCE.getInstance().receiveResultList();
        models = FXCollections.observableArrayList(modelList);
        modelChoiceBox.setItems(models);
        ClientInstance.INSTANCE.getInstance().send("getdate");
        dateList = ClientInstance.INSTANCE.getInstance().receiveResultList();
        dates = FXCollections.observableArrayList(dateList);
        dateChoiceBox.setItems(dates);
    }

    public void addProduction() {
        ClientInstance.INSTANCE.getInstance().send("getidproduct " + modelChoiceBox.getValue());
        id = ClientInstance.INSTANCE.getInstance().receiveResultString();
        if (count.getText().equals("")) {
            ChangingFields.display("Заполните все поля!");
        }
        ClientInstance.INSTANCE.getInstance().send("addproduction " + dateChoiceBox.getValue() + " " + id + " " + count.getText());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ChangingFields.display("Данные добавлены успешно!");
        } else {
            ChangingFields.display("Ошибка добавления данных!");
        }
    }

    public void goBack() {
        modelChoiceBox.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("menuManager", "Меню менеджера");
    }
}
