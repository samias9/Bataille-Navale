//Imports
package model;

/**
 * @class Coordonnee
 * Représente les coordonnées d'un point dans la grille.
 */
public class Coordonnee {
	/**
	 * Ligne de la coordonnée.
	 */
	private int Ligne; //min
	/**
	 * Colonne de la coordonnée.
	 */
	private int Colonne;
	
	private String process = "x";

	public String getProcess() {
		return process+ "\t";
	}
	
	public void setProcess() {
		process = "y";
	}
	public String changeProcess() {
		return "y\t";
	}
	/**
	 * Constructeur par défaut, initialise la coordonnée à (0, 0).
	 */
	public Coordonnee (){
		this.Ligne = 0;
		this.Colonne = 0;
	}

	/**
	 * Constructeur avec des valeurs spécifiques pour la ligne et la colonne.
	 * @param Ligne La ligne de la coordonnée.
	 * @param Colonne La colonne de la coordonnée.
	 */
	public Coordonnee(int Ligne, int Colonne){ //e.g. A1 avec ligne = A et colonne = 1
		this.Ligne = Ligne;
		this.Colonne = Colonne;
	}

	/**
	 * Obtient la ligne de la coordonnée.
	 * @return La ligne de la coordonnée.
	 */
	public int getLigne() {
		return Ligne;
	}

	/**
	 * Définit la ligne de la coordonnée.
	 * @param ligne La nouvelle ligne de la coordonnée.
	 */
	public void setLigne(int ligne) {
		Ligne = ligne;
	}

	/**
	 * Obtient la colonne de la coordonnée.
	 * @return La colonne de la coordonnée.
	 */
	public int getColonne() {
		return Colonne;
	}

	/**
	 * Définit la colonne de la coordonnée.
	 * @param colonne La nouvelle colonne de la coordonnée.
	 */
	public void setColonne(int colonne) {
		Colonne = colonne;
	}

	/**
	 * Compare deux coordonnées.
	 *
	 * @param o Coordonnee à comparer.
	 * @return True si les coordonnées sont égales, sinon false.
	 */
	public boolean equals(Coordonnee o){

        return this.Colonne == o.Colonne
                && this.Ligne == o.Ligne;

    }
	
	public String toString() {
		return   Integer.toString(Ligne) + Colonne;
		
	}
	
}
