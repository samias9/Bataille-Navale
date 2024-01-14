package model;

public enum typeNavires {
	SOUSMARIN(1),//ok
	FREGATE(2),//ok
    CONTRETORPILLEUR(3),//ok
    PORTEAVION(5),//ok
    CUIRASSE(6), //ok
    CROISEUR(4);//

    private final int longueur;

	typeNavires(int longueur) {
        this.longueur = longueur;
    }

    public int getLongueur() 
    {
    	return longueur;
	    }
	}

