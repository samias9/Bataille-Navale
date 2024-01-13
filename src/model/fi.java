/*
@FXML
     void tirer(ActionEvent  event){
        if (event.getSource() instanceof Button) {
            Button clickedButton = (Button) event.getSource();
            System.out.println("Vous avez tirez");

            if (partieEnCours)
            {
                if (joueurActuel != null && joueurAdversaire != null)
                {
                    int ligneTir = Integer.parseInt(ligneAttaque.getText());
                    int colonneTir = Integer.parseInt(colonneAttaque.getText());

                    Coordonnee coordTir = new Coordonnee(ligneTir, colonneTir);
                    joueurActuel.effectuerTir(joueurAdversaire,coordTir);
                    Rectangle correspondingCell = findCorrespondingCell(grilleJoueur, coordTir.getColonne(), coordTir.getLigne());
                    if (correspondingCell != null) {
                        // Change the color of the corresponding cell to green
                        changeRectangleColor(correspondingCell, Color.GREEN);
                    }

                    // Vérifier s'il y a un gagnant
                    if (partie.estGagnant(joueurActuel, joueurAdversaire.getNavires()))
                    {
                        showAlert("Partie terminée", joueurActuel.getPseudo() + " a gagné!");
                        partieEnCours = false;
                    }
                }
                else
                {
                    showAlert("Erreur"," La partie n'est pas correctement initialisée.");
                }
            }
            else
            {
                showAlert("Partie terminée", "La partie est déjà terminée.");
            }
        }

    }
 */