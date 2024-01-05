package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    @FXML
    private TextField pseudoTextField;

    @FXML
    private Button rejoindrePartieButton;

    @FXML
    private Button hebergerPartieButton;

    @FXML
    private void initialize() {
        // Vous pouvez initialiser des choses ici si nécessaire
    }

    @FXML
    public void rejoindrePartieClicked() {
        String pseudo = pseudoTextField.getText();
        if (!pseudo.isEmpty()) {
            // Créez un nouveau joueur avec le pseudo
            Joueur joueur = new Joueur(pseudo);

            //Afficher le pseudo du joueur et ouvrir la fenêtre pour rejoindre une partie
            System.out.println("Joueur créé avec le pseudo : " + joueur.getPseudo());
            loadWindow("../RejoindreWindow.fxml","Rejoindre une partie");
        } else {
            //Si le pseudo est vide message d'erreur à afficher
            showAlert("Veuillez entrer un pseudonyme correct, s'il vous plaît.");
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
    void hebergerPartieClicked() {
        String pseudo = pseudoTextField.getText();
        if (!pseudo.isEmpty()) {
            // Créez un nouveau joueur avec le pseudo
            Joueur joueur = new Joueur(pseudo);

            //Afficher le pseudo du joueur et ouvrir la fenêtre pour rejoindre une partie
            System.out.println("Joueur créé avec le pseudo : " + joueur.getPseudo());
            loadWindow("../HebergerWindow.fxml","Hberger la partie");
        } else {
            //Si le pseudo est vide message d'erreur à afficher
            showAlert("Veuillez entrer un pseudonyme correct, s'il vous plaît.");
        }

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
