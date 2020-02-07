package authorized;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import rppba.ChangingFields;
import rppba.Client;
import rppba.ClientInstance;
import rppba.LoadScene;

import java.awt.*;

public class LogIn {
    @FXML
    private TextField password;
    @FXML
    private TextField user;
    private static String supervisorName;
    private static String managerName;

    public void log(){
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("login " + user.getText() + " " + password.getText());
        String resultString = ClientInstance.INSTANCE.getInstance().receiveResultString();
        if (resultString.equals("admin")) {
            password.getScene().getWindow().hide();
            LoadScene.INSTANCE.getInstance().sceneLoader("menuAdmin", "Admin");
        } else if (resultString.equals("manager")){
            password.getScene().getWindow().hide();
            client.send("getmanagerdata " + user.getText() + " " + password.getText());//БД
            managerName = client.receiveResultString();
            LoadScene.INSTANCE.getInstance().sceneLoader("menuManager", "Manager");
        }else if (resultString.equals("supervisor")){
            password.getScene().getWindow().hide();
            client.send("getclientdata " + user.getText() + " " + password.getText());//БД менять
            supervisorName = client.receiveResultString();
            LoadScene.INSTANCE.getInstance().sceneLoader("menuSupervisor", "Supervisor");
        }else {
            ChangingFields.display("Неверный логин или пароль!");
        }
    }

    public void registerUser() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        user.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("registration", "AddUser");
    }

    public static String getSupervisorName() {
        return supervisorName;
    }

    public static String getManager() {
        return managerName;
    }
}
