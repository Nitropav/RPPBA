package rppba.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import rppba.systems.ChangingFields;
import rppba.systems.ClientInstance;
import rppba.systems.LoadScene;

public class DeleteManager {
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField login;
    @FXML
    private TextField password;

    public void deleteManager() {
        if (name.getText().equals("") || surname.getText().equals("") || login.getText().equals("")
                || password.getText().equals("")) {
            ChangingFields.display("Заполните все поля!");
            return;
        }
        ClientInstance.INSTANCE.getInstance().send("deletemanager " + name.getText() + " " +
                surname.getText() + " " + login.getText() + " " + password.getText());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ChangingFields.display("Данные добавлены успешно!");
        } else {
            ChangingFields.display("Ошибка добавления менеджера");
        }
    }

    public void goBack() {
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("menuDirector", "Меню директора");
    }
}
