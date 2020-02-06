package rppba;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadSceneIns {
    public void sceneLoader(String scene, String nameScene) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/" + scene + ".fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle(nameScene);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
