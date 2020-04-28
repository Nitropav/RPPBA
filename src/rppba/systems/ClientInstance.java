package rppba.systems;

import rppba.systems.Client;

public enum ClientInstance {
    INSTANCE;

    private Client instance;

    ClientInstance() {
        instance = new Client();
    }

    public Client getInstance() {
        return instance;
    }
}
