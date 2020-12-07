package rppba.update;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import rppba.systems.ChangingFields;
import rppba.systems.ClientInstance;
import rppba.systems.LoadScene;

public class UpdateClient{

    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField number;
    @FXML
    private TextField email;
    @FXML
    private TextField id;

    public void updateClient(){
        if (name.getText().equals("") || surname.getText().equals("") || number.getText().equals("")
                || email.getText().equals("") || id.getText().equals("")) {
            ChangingFields.display("Заполните все поля!");
            return;
        }
        ClientInstance.INSTANCE.getInstance().send("updateclient " + id.getText() + " " + name.getText() + " " +
                surname.getText() + " " + number.getText() + " " + email.getText());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ChangingFields.display("Данные обновлены успешно!");
        } else {
            ChangingFields.display("Ошибка обновления данных");
        }
    }

    public void goBack() {
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("menuManager", "Меню менеджера");
    }
}
