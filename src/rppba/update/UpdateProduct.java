package rppba.update;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import rppba.systems.ChangingFields;
import rppba.systems.ClientInstance;
import rppba.systems.LoadScene;

import java.util.ArrayList;

public class UpdateProduct {

    @FXML
    private TextField id;
    @FXML
    private ChoiceBox<String> modelChoiceBox;
    @FXML
    private TextField priceChoiceBox;
    @FXML
    private ChoiceBox<String> typChoiceBox;
    @FXML
    private ChoiceBox<String> shellChoiceBox;
    @FXML
    private ChoiceBox<String> kernelChoiceBox;

    private ArrayList<String> makesList;
    private ObservableList<String> makes;

    public void initialize() {
        ClientInstance.INSTANCE.getInstance().send("gettype");
        makesList = ClientInstance.INSTANCE.getInstance().receiveResultList();
        makes = FXCollections.observableArrayList(makesList);
        typChoiceBox.setItems(makes);
    }

    public void updateProduct(){
        if (id.getText().equals("") || priceChoiceBox.getText().equals("")){
            ChangingFields.display("Заполните все поля!");
        }
        ClientInstance.INSTANCE.getInstance().send("updateproduct " + id.getText() + " " + modelChoiceBox.getValue() + " "
                + Integer.valueOf(priceChoiceBox.getText()) + " " + typChoiceBox.getValue() + " " + shellChoiceBox.getValue()
                + " " + kernelChoiceBox.getValue());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ChangingFields.display("Данные обновлены успешно!");
        } else {
            ChangingFields.display("Ошибка обновления данных!");
        }
    }

    public void getModelProduct(){
        String value = typChoiceBox.getValue();
        if (value.equals("Pen")){
            ObservableList<String> model = FXCollections.observableArrayList("STANDARD_L", "STANDARD_M", "HIGH_L", "HIGH_M", "LOW_L");
            modelChoiceBox.setItems(model);
        }else if (value.equals("Pencil")){
            ObservableList<String> model1 = FXCollections.observableArrayList("BIG_BLACK", "BIG_RED", "BIG_WHITE", "SMALL_RED", "SMALL_BLACK");
            modelChoiceBox.setItems(model1);
        }
        ObservableList<String> shell = FXCollections.observableArrayList(getShell());
        shellChoiceBox.setItems(shell);
        ObservableList<String> kernel = FXCollections.observableArrayList(getKernel());
        kernelChoiceBox.setItems(kernel);
    }
    public ArrayList<String> getShell(){
        String value = typChoiceBox.getValue();
        if (value.equals("Pen")){
            ClientInstance.INSTANCE.getInstance().send("getshellpen");
            ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
            return list;
        }else if (value.equals("Pencil")){
            ClientInstance.INSTANCE.getInstance().send("getshellpencil");
            ArrayList<String> list1 = ClientInstance.INSTANCE.getInstance().receiveResultList();
            return list1;
        }
        return null;
    }

    public ArrayList<String> getKernel(){
        String value1 = typChoiceBox.getValue();
        if (value1.equals("Pen")){
            ClientInstance.INSTANCE.getInstance().send("getkernelpen");
            ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
            return list;
        }else if (value1.equals("Pencil")){
            ClientInstance.INSTANCE.getInstance().send("getkernelpencil");
            ArrayList<String> list1 = ClientInstance.INSTANCE.getInstance().receiveResultList();
            return list1;
        }
        return null;
    }

    public void goBack() {
        typChoiceBox.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("menuManager", "Manager");
    }
}
