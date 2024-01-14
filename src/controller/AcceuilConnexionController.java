package controller;

import ClientNavale.ClientNavale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import model.Joueur;
import model.*;


import java.io.IOException;

public class AcceuilConnexionController {

    static Partie partie;
    @FXML
    private TextField pseudoTextField;
    @FXML
    private Button rejoindrePartieButton;
    @FXML
    private Button hebergerPartieButton;

    /**
     * Action quand le bouton Rejoindre est cliqué
     * @param event
     * @throws IOException
     */
    @FXML
    public void rejoindrePartieClicked(ActionEvent event) throws IOException {
        String pseudo = pseudoTextField.getText();
        if (!pseudo.isEmpty()) {
            // Créez un nouveau joueur avec le pseudo
            Joueur joueur = new Joueur(pseudo);
            partie = new Partie();
            // Associer le joueur à la partie
            partie.setJoueurRejoindre(joueur);
            joueur.setStatus("R");
            partie.numPartie();

            // Afficher le pseudo du joueur et ouvrir la fenêtre pour rejoindre une partie
            System.out.println("Joueur créé avec le pseudo : " + joueur.getPseudo());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/RejoindreWindowVF.fxml"));
            Parent root = loader.load();

            RejoindreWindowController rejoindreController = loader.getController();
            rejoindreController.setPartie(partie);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        }
        else {
            // Si le pseudo est vide, affichez un message d'erreur
            showAlert("Veuillez entrer un pseudo correct, s'il vous plaît.");
        }
    }

    /**
     * Ouvrir une page
     * @param fxmlFile
     * @param title
     */
    private void loadWindow(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Gérez les erreurs de chargement de la fenêtre
        }
    }

    /**
     * Action quand le bouton Héberger est cliqué
     */
    @FXML
    void hebergerPartieClicked(ActionEvent event) throws IOException {
        String pseudo = pseudoTextField.getText();
        if (!pseudo.isEmpty()) {
            // Créez un nouveau joueur avec le pseudo
            Joueur joueur = new Joueur(pseudo);

            // Créez une nouvelle partie et associez le joueur à la partie
            partie = new Partie();

            partie.setJoueurHeberger(joueur);
            joueur.setStatus("H");
            partie.numPartie();

            // Afficher le pseudo du joueur et ouvrir la fenêtre pour héberger une partie
            System.out.println("Joueur créé avec le pseudo : " + joueur.getPseudo());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/HebergerWindow.fxml"));
            Parent root = loader.load();

            // Accédez au contrôleur de la fenêtre HebergerWindow
            HebergerWindowController hebergerController = loader.getController();

            // Passez la partie à HebergerWindowController
            hebergerController.setPartie(partie);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } else {
            // Si le pseudo est vide, affichez un message d'erreur
            showAlert("Veuillez entrer un pseudo correct, s'il vous plaît.");
        }
    }

    /**
     * Action quand le bouton About est cliqué
     * Une présentation est faite dans cette page.
     * @param event
     */
    @FXML
    void showAboutPage(ActionEvent event) {
        // Load the FXML file for the About page
        loadWindow("/vues/AboutPage.fxml", "About");

    }

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
     * Message d'erreur à afficher
     * @param message
     */
    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}