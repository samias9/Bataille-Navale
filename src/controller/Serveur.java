package controller;
import model.Joueur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    private ServerSocket serverSocket;
    private Socket joueurSocket; // Ajout de cette ligne
    private Joueur joueurConnecte; // Ajout de cette ligne

    public void demarrerServeur(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Serveur en attente sur le port " + port);

            // Mettre en attente des connexions
            joueurSocket = serverSocket.accept();
            System.out.println("Joueur connecté depuis " + joueurSocket.getInetAddress());

            // À ce stade, vous pouvez faire ce que vous devez faire avec le joueur connecté
            // par exemple, créer un thread pour gérer la communication avec ce joueur.
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Fermez le serverSocket lorsque vous n'en avez plus besoin
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Ajout de cette méthode pour attendre le joueur
    public Joueur attendreJoueur() {
        return joueurConnecte;
    }
}
