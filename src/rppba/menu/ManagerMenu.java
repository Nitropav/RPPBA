package rppba.menu;

import authorized.LogIn;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import rppba.systems.ChangingFields;
import rppba.systems.ClientInstance;
import rppba.systems.LoadScene;

public class ManagerMenu {

    @FXML
    private Label name;

    public void initialize() {
        name.setText(LogIn.getManager());
    }

    public void addProduct() {
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("addnewproduct", "Add new product");
    }

    public void addNewClient(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("addnewclient", "Add new client");
    }

    public void updateClient(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("updateclient", "Update client");
    }

    public void updateProduct(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("updateproduct", "Update product");
    }

    public void addProduction(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("addproduction", "Add production");
    }

    public void ordersMenu(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("ordersmenu", "Orders menu");
    }

    public void shipOrder(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("shipment", "Menu shipment");
    }

    public void rezervOrder(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("rezervation", "Rezrvation orders");
    }

    public void loyalityClient(){
        ClientInstance.INSTANCE.getInstance().send("loyaltyupdate");
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ChangingFields.display("Данные обновлены успешно!");
        } else {
            ChangingFields.display("Ошибка обновлены данных!");
        }
    }

    public void logOut() {
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("login", "Log IN");
    }
}
