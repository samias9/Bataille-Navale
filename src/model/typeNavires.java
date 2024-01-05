package model;

public enum typeNavires {
	SOUSMARIN(1),
	FREGATE(2),
    CONTRETORPILLEUR(3),
    PORTEAVION(5),
    CUIRASSE(6),
    CROISEUR(4);

    private final int longueur;

	typeNavires(int longueur) {
        this.longueur = longueur;
    }

    public int getLongueur() 
    {
    	return longueur;
	    }
	}

