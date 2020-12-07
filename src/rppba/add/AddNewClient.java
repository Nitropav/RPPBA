package rppba.add;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import rppba.systems.ChangingFields;
import rppba.systems.ClientInstance;
import rppba.systems.LoadScene;

public class AddNewClient {

    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField number;
    @FXML
    private TextField email;

    public void addClient(){
        if (name.getText().equals("") || surname.getText().equals("") || number.getText().equals("")
                || email.getText().equals("")) {
            ChangingFields.display("Заполните все поля!");
            return;
        }
        ClientInstance.INSTANCE.getInstance().send("addnewclient " + name.getText() + " " +
                surname.getText() + " " + number.getText() + " " + email.getText());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ChangingFields.display("Данные добавлены успешно!");
        } else {
            ChangingFields.display("Ошибка добавления клиента");
        }
    }

    public void goBack() {
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("menuManager", "Меню менеджера");
    }
}
