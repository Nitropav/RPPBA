package rppba.add;

import authorized.LogIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import rppba.systems.ChangingFields;
import rppba.systems.ClientInstance;
import rppba.systems.LoadScene;

import java.util.ArrayList;

public class AddDate {
    @FXML
    private Label name;
    @FXML
    private ChoiceBox<String> dateChoiceBox;

    private ArrayList<String> dateList;
    private ObservableList<String> dates;

    public void initialize() {
        name.setText(LogIn.getManager());
        ClientInstance.INSTANCE.getInstance().send("getdateresidual");
        dateList = ClientInstance.INSTANCE.getInstance().receiveResultList();
        dates = FXCollections.observableArrayList(dateList);
        dateChoiceBox.setItems(dates);
    }

    public void addDate(){
        ClientInstance.INSTANCE.getInstance().send("adddateoforders " + dateChoiceBox.getValue());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ChangingFields.display("Данные добавлены успешно!");
        } else {
            ChangingFields.display("Ошибка добавления данных!");
        }
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("menuManager", "Manager menu");
    }

    public void goBack() {
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("addfilling", "Add filling");
    }
}
