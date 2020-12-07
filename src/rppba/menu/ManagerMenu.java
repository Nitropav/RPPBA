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

    public void loyalityClient(){
        ClientInstance.INSTANCE.getInstance().send("loyaltyupdate");
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ChangingFields.display("Данные обновлены успешно!");
        } else {
            ChangingFields.display("Ошибка обновлены данных!");
        }
    }

    public void viewClient(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("viewclient", "Меню просмотра данных о клиентах");
    }

    public void viewResidual(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("viewresidual", "Меню просмотра остатка товаров");
    }

    public void viewProduct(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("viewproduct", "Меню просмотра данных о продукте");
    }

    public void controlLoyalty(){
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("controlloyalty", "Меню управления лояльностью");
    }

    public void logOut() {
        name.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("login", "Log IN");
    }
}
