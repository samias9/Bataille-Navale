package controller;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Coordonnee;
import model.Joueur;
import model.Navire;
import model.Partie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Malheureusement, cette classe n'a jamais été utilisée car nous n'avons pas réussi la partie réseau de ce projet.
 */
public class JouerController {
    private Partie partie;
    private Joueur joueurAdversaire;
    private boolean partieEnCours;
    private Joueur joueurActuel;

    @FXML
    private GridPane grilleJoueur;
    @FXML
    private GridPane grilleAdversaire;
    @FXML
    private MenuButton ligneAttaque;
    @FXML
    private MenuButton colonneAttaque;
    private ArrayList<Navire> naviresJoueurActuel;
    private ArrayList<Navire> naviresJoueurAdversaire;
    private Image image;


    public void setPartie(Partie partie, Joueur joueur) {
        System.out.println("Rejoindre setPartie");
        this.partie = partie;

        this.joueurActuel = joueur;

        if(joueurActuel.getStatus() == "H"){
            this.joueurAdversaire = partie.getJoueurRejoindre();
        }
        else{
            this.joueurAdversaire = partie.getJoueurHeberger();
        }
    }
    @FXML
    private void initialize() {
        // Initialisation de la grille et placement des navires ici
        addMenuItemHandler(ligneAttaque);
        addMenuItemHandler(colonneAttaque);
        image = new Image("/Vues/tir.png");
        // Exemple de code pour montrer une alerte (vous pouvez supprimer ceci après le test)
        showAlert("Jeu initialisé", "COMBATTRE");
    }
    @FXML
    void ShowPlacerNavire(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/ChoisirNaviresVf.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        showAlert("Vous avez abandonnez :(", "OUPS");
    }
    int xy;
    @FXML
    void tirer(ActionEvent event) {
        partieEnCours=true;
        if (event.getSource() instanceof Button) {
            Button clickedButton = (Button) event.getSource();
            System.out.println("Vous avez tiré");

            if (partieEnCours) {
                if (joueurActuel != null && joueurAdversaire != null) {
                    int ligneTir = Integer.parseInt(ligneAttaque.getText());
                    int colonneTir = Integer.parseInt(colonneAttaque.getText());

                    Coordonnee coordTir = new Coordonnee(ligneTir, colonneTir);
                    joueurActuel.effectuerTir(joueurAdversaire, coordTir);


                    if (joueurActuel.getStatus().equals("H")) {
                        // Le joueur actuel est l'hôte, coloriez les coordonnées dans la grilleAdversaire
                        Rectangle correspondingCell = findCorrespondingCell(grilleJoueur, coordTir.getColonne(), coordTir.getLigne());
                        assert correspondingCell != null;
                        xy = (int) correspondingCell.getX() + (int) correspondingCell.getY();

                        if (correspondingCell != null && correspondingCell.toString()!=coordTir.toString()) {
                            System.out.println(xy);
                            changeRectangleColor(correspondingCell, Color.GREEN);
                        }
                        if (correspondingCell != null && correspondingCell.toString()==coordTir.toString()){
                            changeRectangleColor(correspondingCell, Color.RED);
                            System.out.println(xy);
                        }
                    }
                    else
                    {
                        // Le joueur actuel a rejoint la partie, coloriez les coordonnées dans la grilleJoueur
                        Rectangle correspondingCell = findCorrespondingCell(grilleAdversaire, coordTir.getColonne(), coordTir.getLigne());
                        if (correspondingCell != null && correspondingCell.toString()!=coordTir.toString())
                        {
                            assert correspondingCell != null;
                            xy = (int) correspondingCell.getX() + (int) correspondingCell.getY();
                            System.out.println(xy);
                            changeRectangleColor(correspondingCell, Color.GREEN);
                        }
                        if (correspondingCell != null && correspondingCell.toString()==coordTir.toString()){
                            assert correspondingCell != null;
                            xy = (int) correspondingCell.getX() + (int) correspondingCell.getY();
                            System.out.println(xy);
                            changeRectangleColor(correspondingCell, Color.RED);
                        }
                    }

                    // Vérifier s'il y a un gagnant
                    if (partie.estGagnant(joueurActuel, joueurAdversaire.getNavires())) {
                        showAlert("Partie terminée", joueurActuel.getPseudo() + " a gagné!");
                        partieEnCours = false;
                    }
                } else {
                    showAlert("Erreur", " La partie n'est pas correctement initialisée.");
                }
            } else {
                showAlert("Partie terminée", "La partie est déjà terminée.");
            }
        }
    }

    private Rectangle findCorrespondingCell(GridPane gridPane, int columnIndex, int rowIndex) {
        String cellId = "cell_" + rowIndex + "_" + columnIndex;
        System.out.println("Searching for cell ID: " + cellId);

        ObservableList<Node> nodes = gridPane.getChildren();

        for (Node node : nodes) {
            if (node instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) node;

                // Check if the ID matches the expected cell ID
                if (rectangle.getId().equals(cellId)) {
                    System.out.println("Found corresponding cell: " + cellId);
                    return rectangle; // Found the corresponding cell
                }
            }
        }

        System.out.println("Corresponding cell not found: " + cellId);
        return null; // Corresponding cell not found
    }

    void changeRectangleColor(Rectangle rectangle, Color newColor) {
        rectangle.setFill(newColor);
    }

    private void addMenuItemHandler(MenuButton menuButton) {
        ObservableList<MenuItem> items = menuButton.getItems();

        for (MenuItem item : items) {
            item.setOnAction(event -> updateButton(menuButton, item.getText()));
        }
    }

    private void updateButton(MenuButton menuButton, String text) {
        // Update the text of the menuButton itself
        menuButton.setText(text);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
