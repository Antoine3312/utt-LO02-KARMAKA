package model;

import model.carte.PileCartes;
import model.echelle.EchelleKarmique;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

public class EtatPartie {
    private EchelleKarmique echelle;
    private Joueur joueur1;
    private Ordinateur ordinateur;
    private PileCartes source;
    private PileCartes fosse;
    private int numTour;

    public EtatPartie(EchelleKarmique echelle, Joueur joueur1, Ordinateur ordinateur, PileCartes source, PileCartes fosse, int numTour) {
        this.echelle = echelle;
        this.joueur1 = joueur1;
        this.ordinateur = ordinateur;
        this.source = source;
        this.fosse = fosse;
        this.numTour = numTour;
    }
}
