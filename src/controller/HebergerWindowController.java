package controller;
import javafx.application.Application;
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
import java.net.ServerSocket;
import java.net.Socket;

public class HebergerWindowController {
    @FXML
    TextField ipField;
    private int numPort;
    @FXML
    TextField numPortField;
    private Partie partie;
    private static Joueur joueurHeberger;
    @FXML
    void ShowAcceuilConnexion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/AcceuilConnexion.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    public void setPartie(Partie partie) {
        this.partie = partie;
        this.joueurHeberger = partie.getJoueurHeberger();

    }
    public void assignIP(){
        String ipAddress = ipField.getText();
        joueurHeberger.setIP(ipAddress);
        System.out.println("IP Address set to: " + joueurHeberger.getIP());
    }
    public void assignNumPort() {
        numPort = Integer.parseInt(numPortField.getText());
        joueurHeberger.setNPort(numPort);
        System.out.println("N• Port set to: " + joueurHeberger.getNPort());
    }
    @FXML
    public void heberger(ActionEvent event) throws IOException {
        assignIP();
        assignNumPort();
        joueurHeberger.setConnected();
        Joueur joueurRejoindre = partie.getJoueurRejoindre();
        try {
            ServerSocket serverSocket = new ServerSocket(numPort);
            Socket clientSocket = serverSocket.accept();
            // Gérez la connexion avec le joueur qui rejoint
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (joueurRejoindre != null && joueurRejoindre.isConnected()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/PlacerNavires.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Veuillez patienter que l'autre joueur rejoigne.");
            showAlert("Veuillez patienter que l'autre joueur rejoigne.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
