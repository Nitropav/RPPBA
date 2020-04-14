package rppba;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminMenu {

    @FXML
    private Button addManager;

    public void addManager() {
        addManager.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("addmanager", "Add worker");
    }

    public void addSupervisor(){
        addManager.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("addsupervisor", "Add supervisor");
    }

    public void logOut() {
        addManager.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("login", "Log IN");
    }

    public void deleteManager(){
        addManager.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("deletemanager", "Delete Manager");
    }
}
