package rppba.menu;

import authorized.LogIn;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import rppba.systems.LoadScene;

public class AdminMenu {

    @FXML
    private Label name;

    public void initialize() {
        name.setText(LogIn.getManager());
    }

    public void addProduct() {
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("addnewproduct", "Меню добавление нового продукта");
    }

    public void addNewClient(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("addnewclient", "Меню добавление нового клиента");
    }

    public void updateClient(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("updateclient", "Меню изменения данных клиента");
    }

    public void updateProduct(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("updateproduct", "Меню изменения данных о продукте");
    }

    public void addProduction(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("addproduction", "Меню добавления производства");
    }

    public void ordersMenu(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("ordersmenu", "Меню заказа");
    }

    public void shipOrder(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("shipment", "Меню отгрузки товара");
    }

    public void rezervOrder(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("rezervation", "Меню резервации заказа");
    }

    public void controlRezerv(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("controlrezervation", "Меню контроля резервацией");
    }

    public void logOut() {
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("login", "Log IN");
    }
}
