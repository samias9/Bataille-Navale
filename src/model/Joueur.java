package model;
/**
 * @file Joueur.java
 * @brief Définition de la classe Joueur.
 */

import java.util.ArrayList;

/**
 * @class Joueur
 * @brief Représente un joueur dans le jeu.
 */
public class Joueur {
	/**
	 * @brief Pseudo du joueur.
	 */
	private String pseudo;
	private String IP;
	private int NPort;
	/**
	 * @brief Score du joueur
	 */
	private int score;
	private boolean connected;
	private String status;

	public boolean isConnected() {
		return connected;
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	} //H==Hebergeur && R==Rejoindre

	public void setConnected() {
		this.connected = true;
	}

	/**
	 * @brief Tableau de navires du joueur.
	 */
	//private Navire[] navires; //A changer
	private ArrayList<Navire> navires;
	private ArrayList<Coordonnee> tirsRates; //les tirs qu'il a recus mais qui ont touches aucun navire

	public void setIP(String IP) {
		this.IP = IP;
	}

	public void setNPort(int NPort) {
		this.NPort = NPort;
	}

	public String getIP() {
		return IP;
	}

	public int getNPort() {
		return NPort;
	}

	/**
	 * @brief Constructeur par défaut de la classe Joueur.
	 */
	public Joueur() {
		this.pseudo = null;
		this.score = 0;
		
		this.navires = new ArrayList<Navire>();
		this.tirsRates = new ArrayList<>();
	}

	/**
	 * @brief Constructeur avec pseudo de la classe Joueur (comfort).
	 * @param pseudo Le pseudo du joueur.
	 */
	public Joueur(String pseudo) {
		this.pseudo = pseudo;
	    this.tirsRates = new ArrayList<>();
	}

	/**
	 * @brief Obtient le pseudo du joueur.
	 * @return Le pseudo du joueur.
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @brief Obtient le score du joueur.
	 * @return Le score du joueur.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @brief Définit le score du joueur.
	 * @param score Le nouveau score du joueur.
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @brief Obtient le tableau de navires du joueur.
	 * @return Le tableau de navires du joueur.
	 */
	public ArrayList<Navire>  getNavires() { //Nooooooooon
		return navires;
	}

	/**
	 * @brief Définit le tableau de navires du joueur.
	 * @param navires Le nouveau tableau de navires du joueur.
	 */
	public void setNavires(ArrayList<Navire> navires) {
		this.navires = navires;
	}
	
	public ArrayList<Coordonnee> getTirsRates() {
		return tirsRates;
	}
	
	public boolean inTirsRates(Coordonnee coordonnee) {
		/*if(tirsRates.contains(coordonnee)) {
			return true;
		}
		return false;*/
		for (Coordonnee tirRate : tirsRates) {
	        if (tirRate.equals(coordonnee)) {
	            return true;
	        }
	    }
	    return false;
	}

	/**
	 * @brief Ce joueur est attaqué à cette coordonnée.
	 * @param coordonnee La coordonnée de l'attaque.
	 * @return 
	 */
	public void estAttaque(Coordonnee coordonnee) {
		// Vérifier si la coordonnée est contenue dans l'un des navires du joueur
		// Si elle a déjà été touchée, ne rien faire. Sinon, la marquer comme touchée.
		if(navires!=null)
		{
			for (int i=0; i<navires.size();i++)
			{
				if (navires.get(i).contient(coordonnee))
				{
					if (navires.get(i).inPartiesTouchees(coordonnee))
					{
						break;
					}
					else
					{
						navires.get(i).estTouchee(coordonnee);
					}
				}
			}
		}
		else {
			System.err.println("Navires non initialisés");
		}

		tirsRates.add(coordonnee);
		System.out.println("tirer");
		
	}

	/**
	 * @brief Le joueur tire sur un autre joueur à une coordonnée donnée.
	 * @param adversaire Le joueur cible.
	 * @param coordTir La coordonnée de la tentative de tir.
	 */
	public void tirer(Joueur adversaire, Coordonnee coordTir) {
		adversaire.estAttaque(coordTir);
		
	}

	public boolean peutTirer(Coordonnee coordonnee) {
		// Vérifier si la coordonnée n'a pas déjà été visée
		return !inTirsRates(coordonnee);
	}

	public void effectuerTir(Joueur adversaire, Coordonnee coordonnee) {
		if (peutTirer(coordonnee)) {
			adversaire.estAttaque(coordonnee);
			System.out.println("Tir réussi !");
		} else {
			System.out.println("Vous avez déjà tiré à cet endroit.");
		}
	}
}
