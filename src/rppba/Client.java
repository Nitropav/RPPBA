package rppba;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client {
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public String receiveResultString() {
        String result = null;
        try {
            result = (String) inputStream.readObject();
        } catch (Exception e) {

        }
        return result;
    }
}
