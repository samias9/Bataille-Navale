package controller;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.*;

import javafx.scene.control.ToggleGroup;

public class ChoisirNavires {
    private Map<MenuButton, Button> menuButtonToButtonMap = new LinkedHashMap<>();
    private boolean direction = false; //vertical == false
    @FXML
    private Button choisirnavire1;
    @FXML
    private Button choisirnavire2;
    @FXML
    private Button choisirnavire3;
    @FXML
    private Button choisirnavire4;
    @FXML
    private Button choisirnavire5;
    @FXML
    private Button choisirnavire6;
    @FXML
    private Button buttonPrevious;
    @FXML
    private Button btnCombattre;
    @FXML
    private GridPane gridPane;
    @FXML
    private MenuButton LSousMarin;
    @FXML
    private MenuButton CSousMarin;
    @FXML
    private MenuButton LCTorpilleur;
    @FXML
    private MenuButton CCTorpilleur;
    @FXML
    private MenuButton LPorteAvion;
    @FXML
    private MenuButton CPorteAvion;
    @FXML
    private MenuButton LCroiseur;
    @FXML
    private MenuButton CCroiseur;
    @FXML
    private MenuButton LCuirasse;
    @FXML
    private MenuButton CCuirasse;
    @FXML
    private MenuButton LFregate;
    @FXML
    private MenuButton CFregate;
    private Partie partie;
    private ArrayList<Navire> naviresJoueur;
    private Joueur joueur;

    public void setPartie(Partie partie, Joueur joueur) {
        this.partie = partie;
        this.joueur = joueur;
        joueur.setNavires(naviresJoueur);
    }

    @FXML
    void initialize() {
        naviresJoueur = new ArrayList<>(Arrays.asList(null, null, null, null, null, null));

        for (Map.Entry<MenuButton, Button> entry : menuButtonToButtonMap.entrySet()) {
            MenuButton menuButton = entry.getKey();
            System.out.println("MenuButton ID: " + menuButton.getId() + ", Text: " + menuButton.getText());
            addMenuItemHandler(menuButton);
        }
        addMenuItemHandler(LSousMarin);
        addMenuItemHandler(CSousMarin);
        addMenuItemHandler(LCuirasse);
        addMenuItemHandler(CCuirasse);
        addMenuItemHandler(LCroiseur);
        addMenuItemHandler(CCroiseur);
        addMenuItemHandler(LPorteAvion);
        addMenuItemHandler(CPorteAvion);
        addMenuItemHandler(LCTorpilleur);
        addMenuItemHandler(CCTorpilleur);
        addMenuItemHandler(LFregate);
        addMenuItemHandler(CFregate);

        addAssociation(LSousMarin, choisirnavire1);
        addAssociation(CSousMarin, choisirnavire1);
        addAssociation(LFregate, choisirnavire2);
        addAssociation(CFregate, choisirnavire2);
        addAssociation(LCTorpilleur, choisirnavire3);
        addAssociation(CCTorpilleur, choisirnavire3);
        addAssociation(LPorteAvion, choisirnavire4);
        addAssociation(CPorteAvion, choisirnavire4);
        addAssociation(LCuirasse, choisirnavire5);
        addAssociation(CCuirasse, choisirnavire5);
        addAssociation(LCroiseur, choisirnavire6);
        addAssociation(CCroiseur, choisirnavire6);

    }

    @FXML
    void setNavireChoisi(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            colorTheCell(event);
        }}
    @FXML
    void ShowAcceuilConnexion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/AcceuilConnexion.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void saveNavires(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/jouer.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        JouerController jouer = loader.getController();
        jouer.setPartie(partie, joueur);

        stage.setScene(scene);
        stage.show();
    }
    private void addMenuItemHandler(MenuButton menuButton) {
        ObservableList<MenuItem> items = menuButton.getItems();

        for (MenuItem item : items) {
            item.setOnAction(event -> updateButton(menuButton, item.getText()));
        }
    }
    private void addAssociation(MenuButton menuButton, Button button) {
        menuButtonToButtonMap.put(menuButton, button);
    }
    private void updateButton(MenuButton menuButton, String text) {
        // Update the text of the menuButton itself
        menuButton.setText(text);
    }

    private void colorTheCell(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            Button clickedButton = (Button) event.getSource();
            System.out.println("Button ID: " + clickedButton.getId());
            List<MenuButton> associatedMenuButtons = findMenuButtonsByButton(clickedButton);

            if (associatedMenuButtons.size() == 2) {
                MenuButton firstMenuButton;
                // Take the first associated button and read the selected menuItem
                if (associatedMenuButtons.get(0).getText().startsWith("L")) {
                    firstMenuButton = associatedMenuButtons.get(0);
                } else {
                    firstMenuButton = associatedMenuButtons.get(1);
                }

                String selectedLigneItem = getSelectedMenuItem(firstMenuButton);
                MenuButton secondMenuButton;
                if (associatedMenuButtons.get(1).getText().startsWith("C")) {
                    secondMenuButton = associatedMenuButtons.get(1);
                } else {
                    secondMenuButton = associatedMenuButtons.get(0);
                }

                String selectedColonneItem = getSelectedMenuItem(secondMenuButton);

                // Update the attributes ligne and colonne
                if (selectedLigneItem != null && selectedColonneItem != null) {
                    int ligne = Integer.parseInt(selectedLigneItem);
                    int colonne = Integer.parseInt(selectedColonneItem);

                    // Initialisation du navire avec toutes les coordonnées
                    ArrayList<Coordonnee> coordonneesNavire = new ArrayList<>();
                    typeNavires shipType = typeNavires.valueOf(clickedButton.getId());

                    for (int k = 0; k < shipType.getLongueur(); k++)
                    {
                        Coordonnee coord;
                        if (direction) {
                            coord = new Coordonnee(colonne, ligne + k);
                        } else {
                            coord = new Coordonnee(colonne + k, ligne);
                        }
                        coordonneesNavire.add(coord);

                        // Find the corresponding cell in the GridPane
                        Rectangle correspondingCell = findCorrespondingCell(gridPane, coord.getColonne(), coord.getLigne());
                        if (correspondingCell != null) {
                            // Change the color of the corresponding cell to green
                            changeRectangleColor(correspondingCell, Color.GREEN);
                        }
                        naviresJoueur.set(k, new Navire(coordonneesNavire, shipType, direction));
                        System.out.println(naviresJoueur.get(k).toString() + ". "+joueur.getPseudo()+", l'ajout du navire a été fait avec succés. ");
                    }
                    // Associer le navire au joueur
                    //Navire newNavire = new Navire(coordonneesNavire, shipType, direction);
                    //naviresJoueur.add(newNavire);
                    direction = false;
                }
            }
        }
    }
    private String getSelectedMenuItem(MenuButton menuButton) {
        System.out.println("Checking Menu Button ID " + menuButton.getId());

        // Get the selected item from the menuButton
        MenuItem selectedMenuItem = null;
        String data= menuButton.getText();
        System.out.println(menuButton.getText());
        return data;
    }
    private List<MenuButton> findMenuButtonsByButton(Button button) {
        List<MenuButton> associatedMenuButtons = new ArrayList<>();

        for (Map.Entry<MenuButton, Button> entry : menuButtonToButtonMap.entrySet()) {
            if (entry.getValue().equals(button)) {
                associatedMenuButtons.add(entry.getKey());
            }
        }

        // Add logging to check associated menu buttons
        System.out.println("Associated Menu Buttons for Button ID " + button.getId() + ": " + associatedMenuButtons);

        return associatedMenuButtons;
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
    @FXML
    public void setVertical(ActionEvent event) {
        if (event.getSource() instanceof ToggleButton)
        {
            ToggleButton tgBtn = (ToggleButton) event.getSource();
            System.out.println("toogle ID: " + tgBtn.getId());
            if (tgBtn.isSelected())
            {
                System.out.println(tgBtn.getId() + "is selected");
                direction = true;
            }
        }
    }
}