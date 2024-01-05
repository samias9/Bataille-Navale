package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.*;

import javafx.scene.layout.GridPane;
import javafx.util.Pair;


public class ChoisirNavires {

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

    @FXML
    private Rectangle cell11;

    @FXML
    private Rectangle cell12;

    @FXML
    private Rectangle cell13;

    @FXML
    private Rectangle cell14;

    @FXML
    private Rectangle cell15;

    @FXML
    private Rectangle cell16;

    @FXML
    private Rectangle cell17;
    @FXML
    private Rectangle cell18;
    @FXML
    private Rectangle cell19;
    @FXML
    private Rectangle cell1f;

    @FXML
    private Rectangle cell21;

    @FXML
    private Rectangle cell22;

    @FXML
    private Rectangle cell23;

    @FXML
    private Rectangle cell24;
    @FXML
    private Rectangle cell25;

    @FXML
    private Rectangle cell26;
    @FXML
    private Rectangle cell27;
    @FXML
    private Rectangle cell28;
    @FXML
    private Rectangle cell29;
    @FXML
    private Rectangle cell2f;

    @FXML
    private Rectangle cell31;

    @FXML
    private Rectangle cell32;

    @FXML
    private Rectangle cell33;

    @FXML
    private Rectangle cell34;
    @FXML
    private Rectangle cell35;
    @FXML
    private Rectangle cell36;
    @FXML
    private Rectangle cell37;
    @FXML
    private Rectangle cell38;
    @FXML
    private Rectangle cell39;
    @FXML
    private Rectangle cell3f;
    @FXML
    private Rectangle cell41;

    @FXML
    private Rectangle cell42;

    @FXML
    private Rectangle cell43;

    @FXML
    private Rectangle cell44;
    @FXML
    private Rectangle cell45;
    @FXML
    private Rectangle cell46;
    @FXML
    private Rectangle cell47;
    @FXML
    private Rectangle cell48;
    @FXML
    private Rectangle cell49;
    @FXML
    private Rectangle cell4f;
    @FXML
    private Rectangle cell51;

    @FXML
    private Rectangle cell52;
    @FXML
    private Rectangle cell53;
    @FXML
    private Rectangle cell54;
    @FXML
    private Rectangle cell55;

    @FXML
    private Rectangle cell56;
    @FXML
    private Rectangle cell57;
    @FXML
    private Rectangle cell58;
    @FXML
    private Rectangle cell59;
    @FXML
    private Rectangle cell5f;
    @FXML
    private Rectangle cell61;

    @FXML
    private Rectangle cell62;

    @FXML
    private Rectangle cell63;

    @FXML
    private Rectangle cell64;

    @FXML
    private Rectangle cell65;

    @FXML
    private Rectangle cell66;
    @FXML
    private Rectangle cell67;
    @FXML
    private Rectangle cell68;
    @FXML
    private Rectangle cell69;
    @FXML
    private Rectangle cell6f;
    @FXML
    private Rectangle cell71;

    @FXML
    private Rectangle cell72;

    @FXML
    private Rectangle cell73;

    @FXML
    private Rectangle cell74;

    @FXML
    private Rectangle cell75;

    @FXML
    private Rectangle cell76;
    @FXML
    private Rectangle cell77;
    @FXML
    private Rectangle cell78;
    @FXML
    private Rectangle cell79;
    @FXML
    private Rectangle cell7f;
    @FXML
    private Rectangle cell81;

    @FXML
    private Rectangle cell82;

    @FXML
    private Rectangle cell83;

    @FXML
    private Rectangle cell84;

    @FXML
    private Rectangle cell85;

    @FXML
    private Rectangle cell86;
    @FXML
    private Rectangle cell87;
    @FXML
    private Rectangle cell88;
    @FXML
    private Rectangle cell89;
    @FXML
    private Rectangle cell8f;
    @FXML
    private Rectangle cell91;

    @FXML
    private Rectangle cell92;

    @FXML
    private Rectangle cell93;

    @FXML
    private Rectangle cell94;

    @FXML
    private Rectangle cell95;

    @FXML
    private Rectangle cell96;
    @FXML
    private Rectangle cell97;
    @FXML
    private Rectangle cell98;
    @FXML
    private Rectangle cell99;
    @FXML
    private Rectangle cell9f;
    @FXML
    private Rectangle cellf1;

    @FXML
    private Rectangle cellf2;

    @FXML
    private Rectangle cellf3;

    @FXML
    private Rectangle cellf4;

    @FXML
    private Rectangle cellf5;

    @FXML
    private Rectangle cellf6;

    @FXML
    private Rectangle cellf7;
    @FXML
    private Rectangle cellf8;
    @FXML
    private Rectangle cellf9;
    @FXML
    private Rectangle cellff;
    @FXML
    private ImageView imgPolytech;

    // Map to store associations between MenuButtons and Buttons
    private Map<MenuButton, Button> menuButtonToButtonMap = new HashMap<>();
    private Map<Control, Pair<Integer, Integer>> controlToButtonMap = new HashMap<>();


    /**
     * Initialise le contrôleur, ajoutant des gestionnaires d'événements aux MenuButton.
     */
    @FXML
    void initialize() {
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

        // Explicitly define the order of associations for each button

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

    private void colorTheCell2(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            Button clickedButton = (Button) event.getSource();
            System.out.println("Button ID: " + clickedButton.getId());
            List<MenuButton> associatedMenuButtons = findMenuButtonsByButton(clickedButton);

            if (associatedMenuButtons.size() == 2) {
                // Take the first associated button and read the selected menuItem
                MenuButton firstMenuButton = associatedMenuButtons.get(0);
                String selectedLigneItem = getSelectedMenuItem(firstMenuButton);

                // Take the second associated button and read the selected menuItem
                MenuButton secondMenuButton = associatedMenuButtons.get(1);
                String selectedColonneItem = getSelectedMenuItem(secondMenuButton);

                // Update the attributes ligne and colonne
                if (selectedLigneItem != null && selectedColonneItem != null)
                {
                    int ligne = Integer.parseInt(selectedLigneItem);
                    int colonne = Integer.parseInt(selectedColonneItem);

                // Find the corresponding cell in the GridPane
                // Now, call the findCorrespondingCell method with inverted indices
                    Rectangle correspondingCell = findCorrespondingCell(gridPane, ligne, colonne);
                    if (correspondingCell != null)
                    {
                            // Change the color of the corresponding cell to green
                            changeRectangleColor(correspondingCell, Color.GREEN);
                    }
                }
            }
        }
    }

    /********
     * fonctionne à 100%
     */
    private String getSelectedMenuItem(MenuButton menuButton) {
        System.out.println("Checking Menu Button ID " + menuButton.getId());

        // Get the selected item from the menuButton
        MenuItem selectedMenuItem = null;
        String data= menuButton.getText();
        System.out.println(menuButton.getText());
        return data;
    }

    /************
     * fonctionne
     */
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

    //yes

    private Rectangle findCorrespondingCell(GridPane gridPane, int rowIndex, int columnIndex) {
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


    private String determineCorrespondingCellId(Button button) {
        // Assuming the button ID format is "choisirnavireX", where X is a number
        String buttonId = button.getId();

        // Extract the numeric part from the button ID
        String numericPart = buttonId.replaceAll("\\D+", "");

        // Convert the numeric part to lowercase
        numericPart = numericPart.toLowerCase();

        // Append the numeric part to "cell_"
        return "cell_" + numericPart;
    }


    private Button findButtonById(GridPane gridPane, String buttonId) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button && buttonId.equals(node.getId())) {
                return (Button) node;
            }
        }
        return null; // Button not found
    }

/*
    @FXML
    void setNavireChoisi(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            Button clickedButton = (Button) event.getSource();
            System.out.println("Button ID: " + clickedButton.getId());

            // Add your logic for handling the specific button click here
            // For example, you can check the button ID and perform different actions accordingly

            // For changing the color of the corresponding cell, you can call the updateButton method
            MenuButton correspondingMenuButton = determineCorrespondingMenuButton(clickedButton);
            if (correspondingMenuButton != null) {
                updateButton(correspondingMenuButton, correspondingMenuButton.getText());
            }
        }
    }
*/
void changeRectangleColor(Rectangle rectangle, Color newColor) {
    rectangle.setFill(newColor);
}

@FXML
void setNavireChoisi(ActionEvent event) {
    if (event.getSource() instanceof Button) {
        colorTheCell2(event);
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
    void saveNavires(){

    }
}
