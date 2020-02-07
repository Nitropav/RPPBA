package rppba;

public class AdminMenu {
    public void addManager() {
        addManager.getScene().getWindow().hide();
        LoadScene.INSTANCE.getInstance().sceneLoader("addmanager", "Add worker");
    }
}
