package model;

import java.util.ArrayList;
import java.util.Random;

public class Robot extends Joueur {
    private ArrayList<Navire> naviresRobot;
    private boolean rejouer = false;
    private ArrayList<typeNavires> typesDejaChoisis = new ArrayList<>();
    private ArrayList<Coordonnee> tirsRates;

    public Robot() {
        super(generateRandomPseudo());
        this.naviresRobot = new ArrayList<>(6);
        this.tirsRates = new ArrayList<>();
    }

    public ArrayList<Coordonnee> getTirsRates() {
        return tirsRates;
    }

    public boolean inTirsRates(Coordonnee coordonnee) {
        return tirsRates.contains(coordonnee);
    }

    public void estAttaque(Coordonnee coordonnee) {
        boolean tirReussi = false;
        System.out.println("estAttaque de robot");

        if (naviresRobot != null) {
            boolean coordDansNavire = false;

            for (Navire navire : naviresRobot) {
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

    public Coordonnee tirerAleatoirement() {
        Random random = new Random();
        int ligneTir = random.nextInt(10) + 1;
        int colonneTir = random.nextInt(10) + 1;
        return new Coordonnee(ligneTir, colonneTir);
    }

    public void effectuerTir(Joueur adversaire, Coordonnee coordonnee) {
        if (!adversaire.inTirsRates(coordonnee)) {
            System.out.println("La coord n est pas dans tirsRates. ");
            adversaire.estAttaque(coordonnee);
            System.out.println("vous avez tirez !");
        }
        else {
            System.out.println("effectuerTir   !=> "+" Vous avez déjà tiré à cet endroit et se trouve dans les tirsRates.");
        }
    }

    public Navire chosirNavires() {
        Random random = new Random();

        int iLigne, iColonne;
        boolean direction;
        ArrayList<Coordonnee> coordonneesNavire;

        // Obtenir toutes les valeurs de l'énumération typeNavires
        typeNavires[] allTypes = typeNavires.values();

        // Choisir un type de navire aléatoire qui n'a pas déjà été choisi & verifier qu'il chevauche pas avec un autre.
        typeNavires shipType;
        do {
            iLigne = random.nextInt(10) + 1;
            iColonne = random.nextInt(10) + 1;
            direction = random.nextBoolean();

            shipType = allTypes[random.nextInt(allTypes.length)];
            while(typesDejaChoisis.contains(shipType)){
                shipType = allTypes[random.nextInt(allTypes.length)];
            }
            coordonneesNavire = generateCoordonneesNavire(iLigne, iColonne, direction, shipType);
        } while (chevaucheAvecDejaPlace(naviresRobot, coordonneesNavire, shipType, direction));
        typesDejaChoisis.add(shipType);

        Navire navire = new Navire(coordonneesNavire, shipType, direction);
        naviresRobot.add(navire);
        return navire;
    }

    private ArrayList<Coordonnee> generateCoordonneesNavire(int iLigne, int iColonne, boolean direction, typeNavires shipType) {
        ArrayList<Coordonnee> coordonneesNavire = new ArrayList<>();

        for (int k = 0; k < shipType.getLongueur(); k++) {
            Coordonnee coord;
            if (direction) {
                int nouvelleLigne = Math.min(10 - shipType.getLongueur() + 1, Math.max(1, iLigne));
                coord = new Coordonnee(iColonne, nouvelleLigne + k);
            } else {
                int nouvelleColonne = Math.min(10 - shipType.getLongueur() + 1, Math.max(1, iColonne));
                coord = new Coordonnee(nouvelleColonne + k, iLigne);
            }
            coordonneesNavire.add(coord);
        }

        return coordonneesNavire;
    }

    private boolean chevaucheAvecDejaPlace(ArrayList<Navire> navires, ArrayList<Coordonnee> coordonneesNavire, typeNavires shipType, boolean direction) {
        for (Navire navire : navires) {
            if (navire.chevauche(new Navire(coordonneesNavire, shipType, direction))) {
                return true;
            }
        }
        return false;
    }

    private static String generateRandomPseudo() {
        return "Robot" + new Random().nextInt(1000);
    }

    public ArrayList<Navire> getNavires() {
        return naviresRobot;
    }
}
