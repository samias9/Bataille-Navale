package model;
import javafx.scene.control.Alert;

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
	private boolean connected;
	private String status;
	private ArrayList<Navire> navires;
	private ArrayList<Coordonnee> tirsRates; //les tirs qu'il a recus mais qui ont touches aucun navire

	/**
	 * @brief Constructeur par défaut de la classe Joueur.
	 */
	public Joueur() {
		this.pseudo = null;

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

	public String getIP() {
		return IP;
	}

	public void setIP(String IP) {
		this.IP = IP;
	}

	public int getNPort() {
		return NPort;
	}

	public void setNPort(int NPort) {
		this.NPort = NPort;
	}

	/**
	 * @brief Obtient le pseudo du joueur.
	 * @return Le pseudo du joueur.
	 */
	public String getPseudo() {
		return pseudo;
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
		if(tirsRates.contains(coordonnee)) {
			return true;
		}
		return false;
	}

	/**
	 * @brief Ce joueur est attaqué à cette coordonnée.
	 * @param coordonnee La coordonnée de l'attaque.
	 * @return 
	 */
	public void estAttaque(Coordonnee coordonnee) {
		boolean tirReussi = false;
		System.out.println("estAttaque de joueur");

		if (navires != null) {
			boolean coordDansNavire = false;

			for (Navire navire : navires) {
				if (navire.contient(coordonnee)) {
					coordDansNavire = true;

					if (!navire.inPartiesTouchees(coordonnee)) {
						System.out.println("Le navire n'est pas dans partiesTouchees. ");

						if (!tirsRates.contains(coordonnee)) {
							System.out.println("La coord n'est pas dans PartiesTouchees ni dans tirsRates");
							navire.estTouchee(coordonnee);
							tirReussi = true;
							System.out.println("tir réussi 1");
						} else {
							System.out.println("La coord est dans tirsRates");
							tirReussi = false;
						}
					} else {
						System.out.println("La coord est dans partiesTouchees");
						tirReussi = false;
					}
				}
			}

			if (!coordDansNavire) {
				tirsRates.add(coordonnee);
				System.out.println("La coord est dans tirsRates car aucun navire de robot ne contient cette coordonnee.");
			}
		} else {
			System.err.println("Navires non initialisés");
		}
	}

	public void effectuerTir(Joueur adversaire, Coordonnee coordonnee) {
		System.out.println("Inside effectuerTir method de joueur");
		if (!adversaire.inTirsRates(coordonnee)) {
			System.out.println("La coord n est pas dans tirsRates. ");
			adversaire.estAttaque(coordonnee);
			System.out.println("vous avez tirez !");
		}
		else {
			System.out.println("effectuerTir de joueur  !=> "+" Vous avez déjà tiré à cet endroit et se trouve dans les tirsRates.");
		}
	}


}
