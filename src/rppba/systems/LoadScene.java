package rppba.systems;

public enum LoadScene {
    INSTANCE;

    private LoadSceneIns instance;

    LoadScene() {
        instance = new LoadSceneIns();
    }

    public LoadSceneIns getInstance() {
        return instance;
    }
}
