package controller;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;

    public boolean connectToServer(String ipAddress, int numPort) {
        try {
            socket = new Socket(ipAddress, numPort);
            return true;
        } catch (IOException e) {
            System.out.println("Erreur de connexion au serveur: " + e.getMessage());
            return false;
        }
    }
}
