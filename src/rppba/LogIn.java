package rppba;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.awt.*;

public class LogIn {
    @FXML
    private PasswordField password;
    @FXML
    private TextField user;
    public void log(){
        Client client = ClientInstance.INSTANCE.getInstance();
        String resultString =ClientInstance.INSTANCE.getInstance().receiveResultString();
    }
}
