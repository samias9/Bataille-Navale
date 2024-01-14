package controller;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.ArrayList;

public class JouerAvecRobotBat {
    private Partie partie;
    private Robot robotBat;
    private Joueur joueurActuel;
    private boolean partieEnCours;
    private ArrayList<Navire> naviresJoueurActuel;
    private ArrayList<Navire> naviresRobots;

    @FXML
    private GridPane grilleJoueur;
    @FXML
    private GridPane grilleAdversaire;
    @FXML
    private MenuButton ligneAttaque;
    @FXML
    private MenuButton colonneAttaque;

    public void setPartie(Partie partie, Joueur joueur) {
        System.out.println("Rejoindre setPartie");
        this.partie = partie;

        this.joueurActuel = joueur;
        robotBat = new Robot();

        //Générer les navires de RobotBat
        for (int i=0; i<6; i++)
        {
            robotBat.chosirNavires();
        }
        naviresRobots = robotBat.getNavires();
        this.naviresJoueurActuel = joueurActuel.getNavires();
        for (int i=0; i<6;i++)
        {
            if(naviresJoueurActuel.get(i)!=null){
                System.out.println(naviresJoueurActuel.get(i).getcDebut());
                System.out.println("Yay nous avons pris en compte vos choix du navire :) ");
            }
            else {
                System.out.println("It's okay, mais nous avons pas pris en compte vos choix de navires :( ");
            }
        }
    }

    /**
     * Associe des gestionnaires d'événements aux éléments de l'interface utilisateur
     */
    @FXML
    private void initialize() {
        addMenuItemHandler(ligneAttaque);
        addMenuItemHandler(colonneAttaque);
        showAlert("Jeu initialisé", "COMBATTRE");
    }

    /**
     * La méthode appellée quand on clique sur retour
     * @param event
     * @throws IOException
     */
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

    /**
     *Gère l'événement de tir déclenché par le joueur actuel.
     * @param event
     */
    @FXML
    void tirer(ActionEvent event) {
        partieEnCours = true;

        if (event.getSource() instanceof Button) {
            Button clickedButton = (Button) event.getSource();
            System.out.println("Vous avez tiré");

            if (partieEnCours)
            {
                if (joueurActuel != null)
                {
                    System.out.println("Voici les navires de " + joueurActuel.getPseudo());
                    for (int i = 0; i < 6; i++)
                    {
                        System.out.println(joueurActuel.getNavires().get(i).toString());
                    }
                    int ligneTir;
                    int colonneTir;
                    Coordonnee coordTir;

                    ligneTir = Integer.parseInt(ligneAttaque.getText());
                    colonneTir = Integer.parseInt(colonneAttaque.getText());

                    coordTir = new Coordonnee(ligneTir, colonneTir);
                    System.out.println(joueurActuel.getPseudo() + " a tiré sur " + coordTir.toString());
                    joueurActuel.effectuerTir(robotBat, coordTir);

                    System.out.println(joueurActuel.getPseudo() + ", la coordonnée du tir n'a pas touché? " + robotBat.inTirsRates(coordTir));
                    System.out.println("Voici la liste de " + joueurActuel.getPseudo() + " des tirs ratés: " + robotBat.getTirsRates());

                    Rectangle correspondingCell = findCorrespondingCell(grilleAdversaire, coordTir.getColonne(), coordTir.getLigne());

                    if (correspondingCell != null && !robotBat.inTirsRates(coordTir)) {
                        correspondingCell.setFill(Color.GREEN);
                    }
                    if (correspondingCell != null && robotBat.inTirsRates(coordTir)) {
                        correspondingCell.setFill(Color.RED);
                    }

                    // vérifier si la partie est terminée
                    if (partie.estGagnant(joueurActuel, robotBat.getNavires())) {
                        showAlert("Partie terminée", joueurActuel.getPseudo() + " a gagné!");
                        partieEnCours = false;
                    } else {
                        // Le tour du robot
                        tourRobot();
                    }
                } else {
                    showAlert("Erreur", " La partie n'est pas correctement initialisée.");
                }
            } else {
                showAlert("Partie terminée", "La partie est déjà terminée.");
            }
        }
    }

    /**
     *Méthode appellée quand c'est le tour du RobotBat de jouer.
     */
    private void tourRobot() {
        System.out.println("Voici les navires de "+robotBat.getPseudo());
        //Afficher tous les navires du RobotBat (si vous voulez tricher)
        for (int i = 0; i < 6; i++) {
            System.out.println(robotBat.getNavires().get(i).toString());
        }

        Coordonnee coordTirobot = robotBat.tirerAleatoirement();
        Rectangle correspondingCell = findCorrespondingCell(grilleJoueur, coordTirobot.getColonne(), coordTirobot.getLigne());

        robotBat.effectuerTir(joueurActuel, coordTirobot);
        System.out.println(robotBat.getPseudo() + ", la coordonnée du tir n'a pas touché? " + robotBat.inTirsRates(coordTirobot));
        System.out.println("Voici la liste du robots des tirs ratés de RobotBat: " + joueurActuel.getTirsRates());

        if (correspondingCell != null && !joueurActuel.inTirsRates(coordTirobot)) {
            correspondingCell.setFill(Color.GREEN);
        }

        if (correspondingCell != null && joueurActuel.inTirsRates(coordTirobot)) {
            correspondingCell.setFill(Color.RED);
        }

        // vérifier si la patie est terminée
        if (partie.estGagnant(robotBat, joueurActuel.getNavires())) {
            showAlert("Partie terminée", " le RobotBat a gagné!");
            partieEnCours = false;
        }
    }

    /**
     * Recherche et retourne le rectangle correspondant à une cellule spécifiée dans un GridPane.
     *
     * @param gridPane    Le GridPane dans lequel effectuer la recherche.
     * @param columnIndex L'index de colonne de la cellule recherchée.
     * @param rowIndex    L'index de ligne de la cellule recherchée.
     * @return Le Rectangle correspondant à la cellule recherchée s'il est trouvé, sinon null.
     */
    private Rectangle findCorrespondingCell(GridPane gridPane, int columnIndex, int rowIndex) {
        String cellId = "cell_" + rowIndex + "_" + columnIndex;
        System.out.println("Searching for cell ID: " + cellId);

        ObservableList<Node> nodes = gridPane.getChildren();

        for (Node node : nodes) {
            if (node instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) node;

                // Vérifie si l'ID correspond à l'identifiant de cellule attendu.
                if (rectangle.getId().equals(cellId)) {
                    System.out.println("Found corresponding cell: " + cellId);
                    return rectangle; // trouvé
                }
            }
        }

        System.out.println("Corresponding cell not found: " + cellId);
        return null; // Pas trouvé
    }

    /**
     * Associe les gestionnaires d'événements aux éléments du MenuButton
     * @param menuButton
     */
    private void addMenuItemHandler(MenuButton menuButton) {
        ObservableList<MenuItem> items = menuButton.getItems();

        for (MenuItem item : items) {
            item.setOnAction(event -> updateButton(menuButton, item.getText()));
        }
    }

    /**
     * Modifier le texte du menuButton.
     * @param menuButton
     * @param text
     */
    private void updateButton(MenuButton menuButton, String text) {
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