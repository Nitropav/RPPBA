package authorized;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import rppba.*;

public class AddUser {

    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField login;
    @FXML
    private TextField password;

    public void registr() {
        if (name.getText().equals("") || surname.getText().equals("") || login.getText().equals("")
                || password.getText().equals("")){
            ChangingFields.display("Заполнены не все поля!");
            return;
        }
        ClientInstance.INSTANCE.getInstance().send(("addclient " + name.getText() + " " + surname.getText() + " " + login.getText() + " " + password.getText()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ChangingFields.display("Пользователь добавлен успешно!");
            name.getScene().getWindow().hide();
            LoadScene.INSTANCE.getInstance().sceneLoader("logIn", "Log IN");
        } else {
            ChangingFields.display("Ошибка добавления!");
        }
    }

    public void goBack() {
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("menuAdmin", "Menu Admin");
    }
}
