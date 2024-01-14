package model;

import java.util.ArrayList;

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
	 * @param coordonnees tableau des coordonnée du navire.
	 * @param shipType Le type du navire.
	 * @param estVertical True si le navire est vertical, false sinon.
	 */
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

	/**
	 * Obtient la coordonnée de fin du navire.
	 * @return la coordonnée de fin du navire.
	 */
	public Coordonnee getcFin() {
		return cFin;
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
	 * Vérifie si le navire chevauche un autre navire.
	 * @return True si le navire est coulé, sinon false.
	 */
	public boolean chevauche(Navire n) {

		if (this.cDebut.getLigne() == this.cFin.getLigne()) {
			return n.cDebut.getColonne() >= this.cDebut.getColonne()
					&& n.cDebut.getColonne() <= this.cFin.getColonne()
					&& this.cDebut.getLigne() >= n.cDebut.getLigne()
					&& this.cDebut.getLigne() <= n.cFin.getLigne();
		} else {
			return n.cDebut.getLigne() >= this.cDebut.getLigne()
					&& n.cDebut.getLigne() <= this.cFin.getLigne()
					&& this.cDebut.getColonne() <= n.cDebut.getColonne()
					&& this.cDebut.getColonne() <= n.cFin.getColonne();
		}

	}

	/**
	 * Vérifie si le navire est coulé.
	 * @return True si le navire est coulé, sinon false.
	 */
	public boolean estCoule() {
		return nbTouchees == this.partiesTouchees.length;
	}

	@Override
	public String toString(){

		return "Je suis un navire qui commence de " + getcDebut() +" est qui termine à "+getcFin()+". Je suis de type "+getType()+" de taille "+ getType().getLongueur();
	}
}
