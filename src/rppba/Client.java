package rppba;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket socketClient;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public void connect() {
        try {
            socketClient = new Socket("localhost", 2525);
            outputStream = new ObjectOutputStream(socketClient.getOutputStream());
            inputStream = new ObjectInputStream(socketClient.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String receiveResultString() {
        String result = null;
        try {
            result = (String) inputStream.readObject();
        } catch (Exception e) {

        }
        return result;
    }

    public void send(Object obj) {
        try {
            outputStream.writeObject(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean receiveResultBool() {
        boolean result = true;
        try {
            result = (boolean) inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
