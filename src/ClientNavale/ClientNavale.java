package ClientNavale;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Classe pour le client
 */
public class ClientNavale {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private Scanner scanner;


    public ClientNavale(String serverIp, int serverPort) {
        try {
            socket = new Socket(serverIp, serverPort);
            System.out.println("Connecté au serveur : " + serverIp + " sur le port " + serverPort);

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            scanner = new Scanner(System.in);

            // Exemple d'envoi de message au serveur
            //Le client saisi des coordonnées et tir
            while (true) {
                System.out.print("Les coordonées : ");
                String message = scanner.nextLine();
                envoyerTir(message);

                String response = recevoirTir();
                System.out.println("Réponse du serveur : " + response);
            }
            // Exemple d'envoi de coordonnées de tir au serveur
            /*Coordonnee coordTir = new Coordonnee(1, 2); // Remplacez ces coordonnées par celles du tir du joueur
            envoyerCoordonneesTir(coordTir);*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private ServerSocket serverSocket;

    // Méthode pour créer un serveur (hôte)
    public void createServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            // Gérez la connexion avec le joueur qui rejoint
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour se connecter au serveur (joueur qui rejoint)
    public void connectToServer(String serverIp, int serverPort) {
        try {
            Socket socket = new Socket(serverIp, serverPort);
            // Gérez la connexion avec l'hôte
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    /**
     * Envoyer les coordonées de tir saisie par le joueur
     *
     * @param message
     */
    public void envoyerTir(String message) {
        try {
            // Envoyer le message au serveur
            outputStream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recevoir les coordonées envoyés par le Serveur "le joueur hote de la partie"
     *
     * @return
     */
    public String recevoirTir() {
        try {
            // Recevoir le tir du serveur (l'autre joueur)
            return (String) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        String serverIp = "172.25.12.202"; // l'adresse IP du serveur
        int serverPort = 12345; //le port du serveur
        new ClientNavale(serverIp, serverPort);
    }


}
