package model.echelle;

import model.joueur.Joueur;

import java.util.List;

public class EchelleKarmique {
    private final Echellon bousier = new Echellon(4, NomPalier.BOUSIER);
    private final Echellon serpent = new Echellon(5, NomPalier.SERPENT);
    private final Echellon loup = new Echellon(6, NomPalier.LOUP);
    private final Echellon singe = new Echellon(7, NomPalier.SINGE);

    public Echellon getBousier() {
        return bousier;
    }

    public Echellon getSerpent() {
        return serpent;
    }

    public Echellon getLoup() {
        return loup;
    }

    public Echellon getSinge() {
        return singe;
    }
}
