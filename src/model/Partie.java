package model;

import java.util.*;

public class Partie {
	private static int i; //Pour compter le nombre de partie commencée
	private static ArrayList<typeNavires> typeNaviresJ1;
    private static ArrayList<typeNavires> typeNaviresJ2;
	private static ArrayList<typeNavires> naviresChoisisJ1;
	private static ArrayList<typeNavires> naviresChoisisJ2;
	private static ArrayList<Navire> navires1;
	private static ArrayList<Navire> navires2;
	private static Joueur joueurHeberger;
	private static Robot joueurRobot;
	private static Joueur joueurRejoindre;
	private static boolean avecRobot;

	public Partie(){
		i++;
	}
	public void setAvecRobot(boolean avecRobot){
		this.avecRobot = avecRobot;
	}

	public boolean getAvecRobot(){ return avecRobot;}

	public static void numPartie(){
		System.out.println("Je suis la partie en cours numéro: "+i);
	}

    public static Scanner in;
	public static final String msg0 = "veuillez placer les 6 navires";
    public static final String msgCoord = "Entrez la coordonnée de début (ligne colonne):";
    public static final String msgDirection = "Le navire est vertical? (true/false):";
    public static final String msgChoisirNavire = "\n" +
			"            Choisissez le type de navire:\n" +
			"            1. SOUSMARIN\n" +
			"            2. FREGATE\n" +
			"            3. CONTRETORPILLEUR\n" +
			"            4  PORTEAVION\n" +
			"            5. CUIRASSE\n" +
			"            6. CROISEUR";

	public static void setJoueurHeberger(Joueur joueur) {
		joueurHeberger = joueur;
	}

	public static void setJoueurRejoindre(Joueur joueur) {
		joueurRejoindre = joueur;
	}
	public static void setJoueurRobot(Robot robot) {
		joueurRobot = robot;
	}
	public static Joueur getJoueurHeberger() {
		return joueurHeberger;
	}
	public static Joueur getJoueurRejoindre() {
		return joueurRejoindre;
	}
	private static void placerNavires() {
	
	    for (int j = 0; j < 2; j++) 
	    {
	        Joueur joueur = (j == 0) ? joueurHeberger : joueurRejoindre;
	        ArrayList<typeNavires> typeNaviresC = (j == 0) ? typeNaviresJ1 : typeNaviresJ2;
	
	        System.out.println(joueur.getPseudo() + ", " + msg0);
	
	        for (int i = 0; i < 1; i++) 
	        {
	            System.out.println("Navire nº" + (i + 1) + ", C'est parti :)");
	
	            // Entrée des coordonnées de début
	            System.out.println(msgCoord);
	            int ligneDebut = in.nextInt();
	            int colonneDebut = in.nextInt();
	            System.out.println("Coordonnées saisies : " + (ligneDebut ) + " " + (colonneDebut ));
	
	            // Entrée des dimensions du navire
	            System.out.println(msgChoisirNavire);
	            in.nextLine(); // Consume the newline character
	            typeNavires shipType = typeNavires.valueOf(in.nextLine().toUpperCase());
	
	            System.out.println(msgDirection);
	            boolean estVertical = Boolean.parseBoolean(in.next());
	
	            // Initialisation du navire avec toutes les coordonnées
	            ArrayList<Coordonnee> coordonneesNavires = new ArrayList<>();
	            for (int k = 0; k < shipType.getLongueur(); k++) 
	            {
	                Coordonnee coord = (estVertical) ?
	                        new Coordonnee(ligneDebut + k, colonneDebut) :
	                        new Coordonnee(ligneDebut, colonneDebut + k);
	                coordonneesNavires.add(coord);
	            }
	
	            Navire newNavire = new Navire(coordonneesNavires, shipType, estVertical);
	
	            // Sélection en fonction du joueur
	            ArrayList<Navire> naviresFinal = (j == 0) ? navires1 : navires2;
	            naviresFinal.add(newNavire);
	        }
	    }
	}

	private static void afficherGrille(Joueur joueur) {
        System.out.println("Grille de " + joueur.getPseudo() + ":");

        System.out.print("   ");
        for (char c = 'A'; c <= 'J'; c++) {
            System.out.print(c + " ");
        }

        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print((i + 1) + " |");

            for (int j = 0; j < 10; j++) {
                Coordonnee coord = new Coordonnee(i + 1, j + 1);
                char marque = obtenirMarque(joueur,coord);
                System.out.print(" " + marque);
            }

            System.out.println();
        }
    }
	
	private static char obtenirMarque(Joueur joueur, Coordonnee coord) {
	    // Vérifier si la coordonnée correspond à un navire du joueur
	    for (Navire navire : joueur.getNavires()) 
	    {
	        if (navire.contient(coord)) 
	        {
	            if (navire.inPartiesTouchees(coord)) 
	            {
	                return 'X'; // Partie touchée 
	            } 
	             
	            else 
	            {
	                return 'N'; // Partie non touchée 
	            }
	        }
	    }
	
	    // Aucun navire trouvé, la case est vide
	    return '.';
	}

	public static boolean estGagnant(Joueur joueur, ArrayList<Navire> naviresAdversaire) {
	    // Vérifier si tous les navires de l'adversaire ont été coulés
	    for (Navire navire : naviresAdversaire) {
	        if (!navire.estCoule()) {
	            return false; // Il reste au moins un navire non coulé
	        }
	    }
	    // Tous les navires de l'adversaire ont été coulés, le joueur est gagnant
	    return true;
	}
	
	public static void informerPlacement(Joueur j) {
		
		System.out.println("Joueur " + j.getPseudo() + " a placé ses bateaux :");
	    
	    for (int i = 0; i < j.getNavires().size(); i++) 
	    {
	        Navire navire = j.getNavires().get(i);
	        Coordonnee debut = navire.getcDebut();
	        System.out.println("Navire nº" + (i + 1) + ": " + debut.getLigne() + " " + debut.getColonne());
	    }
	}
	
	public static void afficherGrilleTirs(Joueur adv) {
	    // Assuming the grid size is 10x10 for illustration purposes
	    int gridSize = 10;

	    char[][] grille = new char[gridSize][gridSize];

	    // Initialize the grid with empty spaces
	    for (int i = 0; i < gridSize; i++) {
	        for (int j = 1; j < gridSize; j++) {
	            grille[i][j] = '\u00B7'; // Unicode character for middle dot (empty space)
	        }
	    }

	    // Mark coordinates in tirsRates as 'L'
	    for (Coordonnee coord : adv.getTirsRates()) {
	        grille[coord.getLigne()-1 ][coord.getColonne()] = 'L';
	    }

	    // Display the grid with row and column headers
	    System.out.print("  ");
	    for (char c = 'A'; c < 'A' + gridSize; c++) {
	        System.out.print(c + " ");
	    }
	    System.out.println();

	    for (int i = 0; i < gridSize; i++) {
	        System.out.print((i + 1) + " |");
	        for (int j = 0; j < gridSize; j++) {
	            System.out.print(" " + grille[i][j]);
	        }
	        System.out.println();
	    }
	    System.out.println();
	}

	/**
	 * Pour jouer en mode console, veuillez exécuter MAINconsole.
	 */
	public static void run() {
		in = new Scanner(System.in);
		// Sets pour suivre les types de navires choisis pour chaque joueurs
	    naviresChoisisJ1 = new ArrayList<>();
	    naviresChoisisJ2 = new ArrayList<>();
		
	
	    // Tableaux pour stocker les navires de chaque joueur
	    navires1 = new ArrayList<Navire>();
	    navires2 = new ArrayList<Navire>();
	
	    // Entrée des pseudos pour les joueurs
	    System.out.println("Joueur1: Entrer votre Pseudo");
	    joueurHeberger = new Joueur(in.nextLine());
	    
	    System.out.println("Joueur2: Entrer votre Pseudo");
	    joueurRejoindre = new Joueur(in.nextLine());
	
	    // Assigner les tableaux de navires à chaque joueur
	    joueurHeberger.setNavires(navires1);
	    joueurRejoindre.setNavires(navires2);
	
	    //Placer les navires
	    placerNavires();
		
	    informerPlacement(joueurHeberger);
	    informerPlacement(joueurRejoindre);
	    
	    //Debut jeu
	    System.out.println("Debut du jeu!");
	    
	    boolean partieEnCours = true;
	    Joueur joueurActuel = joueurHeberger;

	    while (partieEnCours) {
	    	afficherGrille(joueurActuel);

            System.out.println(joueurActuel.getPseudo() + ", entrez les coordonnées pour tirer :");
            int ligneTir = in.nextInt();
            int colonneTir = in.nextInt();

            Coordonnee coordTir = new Coordonnee(ligneTir, colonneTir);
			joueurActuel.effectuerTir(joueurActuel == joueurHeberger ? joueurRejoindre : joueurHeberger, coordTir);

            if (joueurActuel == joueurHeberger) {
            	afficherGrilleTirs(joueurRejoindre);
            } else {
            	afficherGrilleTirs(joueurHeberger);
            }
            if (estGagnant(joueurHeberger, navires1)) {
                System.out.println(joueurHeberger.getPseudo() + " a gagné!");
                partieEnCours = false;
            } else if (estGagnant(joueurRejoindre, navires2)) {
                System.out.println(joueurRejoindre.getPseudo() + " a gagné!");
                partieEnCours = false;
            }

            joueurActuel = joueurActuel == joueurHeberger ? joueurRejoindre : joueurHeberger;
        }

        in.close();
		
	}
}

