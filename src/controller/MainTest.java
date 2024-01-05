package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the main FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/PlacerNavires.fxml"));
        Parent root = loader.load();
        // Show the main window
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bataille Navale");
        primaryStage.show();
    }
}

