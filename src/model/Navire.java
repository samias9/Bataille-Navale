package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @class navire
 * Représente un navire dans le jeu.
 */
public class Navire {
	private Coordonnee cDebut;
	private Coordonnee cFin;
	private typeNavires shipType;
	private Coordonnee[] partiesTouchees; //A changer
	private int nbTouchees;

	/**
	 * Constructeur par défaut de la classe navire.
	 */
	public Navire(){
		this.cDebut=null;
		this.cFin = null;
		this.partiesTouchees = null;
		this.nbTouchees = 0;
		this.shipType = null;
	}

	/**
	 * Constructeur avec paramètres de la classe navire.
	 * @param cDebut Coordonnée de début du navire.
	 * @param shipType Le type du navire.
	 * @param estV True si le navire est vertical, false sinon.
	 */
	public Navire(Coordonnee cDebut, typeNavires shipType, boolean estV){
		this.cDebut = cDebut;
		this.shipType = shipType;
		
		int longueur = shipType.getLongueur();
		
		if (estV) {

			cFin = new Coordonnee(this.cDebut.getColonne(), this.cDebut.getLigne() + longueur - 1);

        } else {

        	cFin = new Coordonnee(this.cDebut.getColonne() + longueur - 1, this.cDebut.getLigne());

        }

        partiesTouchees = new Coordonnee[longueur];
        for (int i = 0; i < longueur; i++) {
            partiesTouchees[i] = null; // Initialise toutes les parties comme non touchées
        }
        nbTouchees = 0;
	}
	
	public Navire(ArrayList<Coordonnee> coordonnees, typeNavires shipType, boolean estVertical) {
	    if (coordonnees == null || coordonnees.isEmpty()) {
	        throw new IllegalArgumentException("La liste des coordonnées ne peut pas être nulle ou vide.");
	    }

	    this.shipType = shipType;

	    // Sélection des coordonnées de début et de fin en fonction de l'orientation
	    if (estVertical) {
	        this.cDebut = coordonnees.get(0);
	        this.cFin = coordonnees.get(coordonnees.size() - 1);
	    } else {
	        this.cDebut = coordonnees.get(0);
	        this.cFin = coordonnees.get(coordonnees.size() - 1);
	    }

	    this.partiesTouchees = new Coordonnee[coordonnees.size()];
	    this.nbTouchees = 0;

	    for (int i = 0; i < coordonnees.size(); i++) {
	        this.partiesTouchees[i] = null; // Initialise toutes les parties comme non touchées
	    }
	}


	/**
	 * Obtient la coordonnée de début du navire.
	 * @return La coordonnée de début du navire.
	 */
	public Coordonnee getcDebut() {
		return cDebut;
	}

	public Coordonnee getcFin() {
		return cFin;
	}

	/**
	 * Obtient le tableau des coordonnées touchées du navire.
	 * @return Le tableau des coordonnées touchées du navire.
	 */
	public Coordonnee[] getPartiesTouchees() {
		return partiesTouchees;
	}

	/**
	 * Définit le tableau des coordonnées touchées du navire.
	 * @param partiesTouchees Le nouveau tableau des coordonnées touchées du navire.
	 */
	public void setPartiesTouchees(Coordonnee[] partiesTouchees) {
		this.partiesTouchees = partiesTouchees;
	}

	public typeNavires getType() {
		return shipType;
	}

	/**
	 * Obtient le nombre de parties touchées du navire.
	 * @return Le nombre de parties touchées du navire.
	 */
	public int getNbTouchees() {
		return nbTouchees;
	}

	/**
	 * Définit le nombre de parties touchées du navire.
	 * @param nbTouchees Le nouveau nombre de parties touchées du navire.
	 */
	public void setNbTouchees(int nbTouchees) {
		this.nbTouchees = nbTouchees;
	}

	/**
	 * Vérifie si la coordonnée passée en paramètre est dans partiesTouchees.
	 * @param coordonnee La coordonnée à vérifier.
	 * @return True si la coordonnée est dans partiesTouchees, sinon false.
	 */
	public boolean inPartiesTouchees(Coordonnee coordonnee) {
		
	    if (partiesTouchees != null) {
            for (Coordonnee partiesTouchee : partiesTouchees) {
                if (partiesTouchee != null && partiesTouchee.equals(coordonnee)) {
                    return true;
                }
            }
	    }
	    return false;
	    
	}


	/**
	 * Vérifie si la coordonnée est contenue dans le navire.
	 * @param coordonnee La coordonnée à vérifier.
	 * @return True si la coordonnée est contenue dans le navire, sinon false.
	 */
	public boolean contient(Coordonnee coordonnee) {
		return coordonnee.getColonne() >= cDebut.getColonne()
                && coordonnee.getColonne() <= cFin.getColonne()
                && coordonnee.getLigne() >= cDebut.getLigne()
                && coordonnee.getLigne() <= cFin.getLigne();
		
	}

	/**
	 * Vérifie si le tir touche un navire.
	 * Si c'est le cas, et si la coordonnée n'est pas déjà dans le tableau, nbTouchées et partiesTouchées seront mis à jour.
	 * @param coordonnee La coordonnée du tir.
	 * @return 
	 */
	public void estTouchee(Coordonnee coordonnee) {
		
		
		if (!inPartiesTouchees(coordonnee)) {
			if (this.contient(coordonnee)) {
				this.partiesTouchees[nbTouchees] = coordonnee;
				this.nbTouchees = nbTouchees+1;
				System.out.println("Touché !");
			}
		}
		
	}

	/**
	 * Vérifie si le navire est coulé.
	 * @return True si le navire est coulé, sinon false.
	 */
	public boolean estCoule() {
		return nbTouchees == this.partiesTouchees.length;
	}

	public static class AffichageGrille {

		/**
		 * Affiche la grille du joueur spécifié.
		 *
		 * @param joueur Le joueur pour lequel la grille doit être affichée.
		 */
		public static void afficherGrille(Joueur joueur) {
			System.out.println("Grille de " + joueur.getPseudo() + ":");

			// Affichage de l'en-tête des colonnes (A-J)
			System.out.print("   ");
			for (char c = 'A'; c <= 'J'; c++) {
				System.out.print(c + " ");
			}
			System.out.println();

			// Affichage de chaque ligne de la grille
			for (int i = 1; i <= 10; i++) {
				// Affichage du numéro de ligne (1-10)
				System.out.print(i + " |");

				// Affichage des cases de la ligne
				for (int j = 1; j <= 10; j++) {
					Coordonnee coord = new Coordonnee(i, j);
					char marque = obtenirMarque(joueur, coord);
					System.out.print(" " + marque);
				}

				System.out.println();
			}
		}

		/**
		 * Affiche la grille du joueur avec le tir effectué à la coordonnée spécifiée.
		 *
		 * @param joueur    Le joueur pour lequel la grille doit être affichée.
		 * @param coordTir  Les coordonnées du tir effectué.
		 */
		public static void afficherGrilleAvecTir(Joueur joueur, Coordonnee coordTir) {
			System.out.println("Grille de " + joueur.getPseudo() + ":");

			// Affichage de l'en-tête des colonnes (A-J)
			System.out.print("   ");
			for (char c = 'A'; c <= 'J'; c++) {
				System.out.print(c + " ");
			}
			System.out.println();

			// Affichage de chaque ligne de la grille
			for (int i = 1; i <= 10; i++) {
				// Affichage du numéro de ligne (1-10)
				System.out.print(i + " |");

				// Affichage des cases de la ligne
				for (int j = 1; j <= 10; j++) {
					Coordonnee coord = new Coordonnee(i, j);
					char marque = (coord.equals(coordTir)) ? 'X' : obtenirMarque(joueur, coord);
					System.out.print(" " + marque);
				}

				System.out.println();
			}
		}

		/**
		 * Obtient la marque à afficher pour une coordonnée spécifique du joueur.
		 *
		 * @param joueur Le joueur pour lequel la marque doit être obtenue.
		 * @param coord  Les coordonnées pour lesquelles la marque doit être obtenue.
		 * @return La marque à afficher ('X' pour partie touchée, 'O' pour partie non touchée, '.' pour case vide).
		 */
		private static char obtenirMarque(Joueur joueur, Coordonnee coord) {
			// Vérifier si la coordonnée correspond à un navire du joueur
			for (Navire navire : joueur.getNavires()) {
				if (navire.contient(coord) && navire.inPartiesTouchees(coord)) {
					return 'X'; // Partie touchée
				} else if (navire.contient(coord)) {
					return 'O'; // Partie non touchée (navire présent)
				}
			}

			// Aucun navire trouvé, la case est vide
			return '.';
		}

		/**
		 * Vérifie si le type de navire est valide et non encore choisi.
		 *
		 * @param shipType           Le type de navire à vérifier.
		 * @param chosenShipTypes    L'ensemble des types de navires déjà choisis.
		 * @return true si le type de navire est valide et non encore choisi, sinon false.
		 */
		private static boolean typeValide(typeNavires shipType, Set<typeNavires> chosenShipTypes) {
			return chosenShipTypes.add(shipType);
		}

		/**
		 * Fonction principale pour la configuration des navires et le début du jeu.
		 *
		 * @param args Les arguments de la ligne de commande.
		 */
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);

			// 1: Configuration Navires: nombre de navires, choix des navires
			System.out.println("Configuration de la partie :)");
			System.out.println("Nombre de navire pour cette partie?)");

			// Nombre de navires pour la partie
			int nbNavires = sc.nextInt();

			// Sets pour suivre les types de navires choisis pour chaque joueurs
			Set<typeNavires> naviresChoisisJ1 = new HashSet<>();
			Set<typeNavires> naviresChoisisJ2 = new HashSet<>();

			// Tableaux pour stocker les navires de chaque joueur
			ArrayList<Navire> Navires1 = new ArrayList<>();
			ArrayList<Navire> Navires2 = new ArrayList<>();

			sc.nextLine(); // Consommer le caractère de nouvelle ligne

			// Entrée des pseudos pour les joueurs
			System.out.println("Joueur1: Entrer votre Pseudo");
			Joueur joueur1 = new Joueur(sc.nextLine());

			System.out.println("Joueur2: Entrer votre Pseudo");
			Joueur joueur2 = new Joueur(sc.nextLine());

			// Assigner les tableaux de navires à chaque joueur
			joueur1.setNavires(Navires1);
			joueur2.setNavires(Navires2);

			// Boucle pour chaque joueur
			for (int j = 0; j < 2; j++) {
				Joueur joueur = (j == 0) ? joueur1 : joueur2;
				Set<typeNavires> typeNaviresChoisis = (j == 0) ? naviresChoisisJ1 : naviresChoisisJ2;

				// Entrée des coordonnées et des types de navires pour chaque navire
				System.out.println(joueur.getPseudo() + ", veuillez placer vos " + nbNavires + " navires");

				for (int i = 0; i < nbNavires; i++) {
					System.out.println("Navire nº" + (i + 1) + ", C'est parti :)");

					// Entrée des coordonnées de début
					System.out.println("Entrez la coordonnée de début (ligne colonne):");
					int ligneDebut = sc.nextInt();
					int colonneDebut = sc.nextInt();
					Coordonnee cDebut = new Coordonnee(ligneDebut, colonneDebut);

					// Saisie du type de navire jusqu'à ce qu'un type valide et non encore choisi soit entré
					typeNavires shipType;
					do {
						System.out.println("Choisissez le type de navire (TYPE1, TYPE2, ...):");
						sc.nextLine(); // Consommer le caractère de nouvelle ligne
						shipType = typeNavires.valueOf(sc.nextLine().toUpperCase());

					} while (!typeValide(shipType, typeNaviresChoisis));

					// Ajouter le type choisi à l'ensemble des types choisis
					typeNaviresChoisis.add(shipType);

					System.out.println("Le navire est vertical? (true/false):");
					boolean estVertical = sc.nextBoolean();

					// Sélectionner le tableau de navires en fonction du joueur actuel
					ArrayList<Navire> naviresFinal = (j == 0) ? Navires1 : Navires2;
					naviresFinal.set(i, new Navire(cDebut, shipType, estVertical));
					// Créer un nouveau navire à partir des coordonnées et du type choisis
					Navire nouveauNavire = new Navire(cDebut, shipType, estVertical);

					boolean seChevauche = false;
					for (Navire navireExistant : naviresFinal) {
						if (navireExistant != null && navireExistant.seChevauche(nouveauNavire)) {
							seChevauche = true;
							System.out.println("Le navire se chevauche avec un navire existant. Veuillez le replacer.");
							break;
						}
					}

					// Si le nouveau navire ne se chevauche pas, l'ajouter à la liste des navires
					if (!seChevauche) {
						naviresFinal.add(nouveauNavire);
					}
				}

			}
			//sc.close();
			System.out.println("joueur " + joueur1.getPseudo() + " a place sont bateau 1 :"
					+ joueur1.getNavires().get(0).getcDebut().toString());

			//Debut jeu
			System.out.println("Debut du jeu!");

			// Affichage des grilles des deux joueurs
			afficherGrille(joueur1);
			afficherGrille(joueur2);


			System.out.println(joueur1.getPseudo() + " Vous tirez sur quelle ligne?");
			int ligneDebut = sc.nextInt();

			System.out.println(joueur1.getPseudo() + " Vous tirez sur quelle colonne?");
			int colonneDebut = sc.nextInt();
			Coordonnee coord = new Coordonnee(ligneDebut, colonneDebut);

			joueur1.tirer(joueur2, coord);
			afficherGrilleAvecTir(joueur2, coord);

		}
	}

    /**
     * Vérifie si le navire actuel se chevauche avec un autre navire.
     *
     * @param n Le navire avec lequel vérifier le chevauchement.
     * @return true si les navires se chevauchent, sinon false.
     */
    public boolean seChevauche(Navire n) {
        // Vérification si les navires sont alignés horizontalement
        if (this.cDebut.getLigne() == this.cFin.getLigne()) {
            // Vérification des conditions de chevauchement
            return n.cDebut.getColonne() >= this.cDebut.getColonne()
                    && n.cDebut.getColonne() <= this.cFin.getColonne()
                    && this.cDebut.getLigne() >= n.cDebut.getLigne()
                    && this.cDebut.getLigne() <= n.cFin.getLigne();
        } else { // Les navires sont alignés verticalement
            // Vérification des conditions de chevauchement
            return n.cDebut.getLigne() >= this.cDebut.getLigne()
                    && n.cDebut.getLigne() <= this.cFin.getLigne()
                    && this.cDebut.getColonne() <= n.cDebut.getColonne()
                    && this.cDebut.getColonne() <= n.cFin.getColonne();
        }
    }

}
