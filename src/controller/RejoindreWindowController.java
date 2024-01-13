package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Joueur;
import model.Partie;

import java.io.IOException;
import java.net.Socket;

/**
 * Contrôleur pour l'interface utilisateur permettant de rejoindre une partie existante.
 */
public class RejoindreWindowController {
    @FXML
    private TextField ipField;
    @FXML
    private TextField numPortField;
    private int numPort;
    private String ipAddress;
    private Partie partie;
    private static Joueur joueurRejoindre;

    /**
     * Initialise la partie en cours.
     *
     * @param partie La partie en cours.
     */
    public void setPartie(Partie partie) {
        System.out.println("Rejoindre setPartie");
        this.partie = partie;
        this.joueurRejoindre = partie.getJoueurRejoindre();
    }

    /**
     * Vérifie si l'adresse IP entrée correspond à celle du joueur hébergeur.
     *
     * @return true si l'adresse IP est valide, sinon false.
     */

    private boolean verifyIP() {
        ipAddress = ipField.getText(); //j'ai supprimé  String
        Joueur joueurHeberger = partie.getJoueurHeberger();
        return joueurHeberger != null && joueurHeberger.getIP() != null && joueurHeberger.getIP().equals(ipAddress);
    }

    /**
     * Vérifie si le numéro de port entré correspond à celui du joueur hébergeur.
     *
     * @return true si le numéro de port est valide, sinon false.
     */

    private boolean verifyNumPort() {
        //int numPort;
        try {
            numPort = Integer.parseInt(numPortField.getText());
        } catch (NumberFormatException e) {
            return false; // La conversion échoue si ce n'est pas un nombre
        }
        return partie.getJoueurHeberger() != null && partie.getJoueurHeberger().getNPort() == numPort;
    }

    /**
     * Gère l'événement de rejoindre une partie existante.
     *
     * @param event L'événement d'action déclenché.
     * @throws IOException Si une erreur d'entrée/sortie survient.
     */
    @FXML
    private void rejoindrePartie(ActionEvent event) throws IOException {
        if (partie.getJoueurHeberger() != null && partie.getJoueurHeberger().isConnected()) {
            System.out.println("verifyIP() and verifyNumPort() are true.");

            joueurRejoindre.setConnected();
            try {
                Socket socket = new Socket(ipAddress, numPort);
                // Gérez la connexion avec l'hôte
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (verifyIP() && verifyNumPort()) {
                System.out.println("Joueur heberger is connected.");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/ChoisirNaviresVf.fxml"));
                Parent root = loader.load();

                ChoisirNavires chosirNaviresController = loader.getController();
                chosirNaviresController.setPartie(partie, joueurRejoindre);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("Veuillez patienter que l'autre joueur rejoigne.");
                showAlert("Veuillez patienter que l'autre joueur rejoigne.");
            }
            } else {
            System.out.println("Connexion à l'IP " + ipField.getText() + " via le port " + numPortField.getText());
            System.out.println("Veuillez patienter que l'autre joueur rejoigne.");
            showAlert("Veuillez patienter que l'autre joueur rejoigne.");
        }
    }

    /**
     * Gère l'événement de retour à l'écran d'accueil de connexion.
     *
     * @param event L'événement d'action déclenché.
     * @throws IOException Si une erreur d'entrée/sortie survient.
     */
    @FXML
    void ShowAcceuilConnexion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/AcceuilConnexion.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Affiche une boîte de dialogue d'information avec le message spécifié.
     *
     * @param message Le message à afficher.
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
