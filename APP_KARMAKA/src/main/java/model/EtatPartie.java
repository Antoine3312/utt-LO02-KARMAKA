package model;

import model.carte.PileCartes;
import model.echelle.EchelleKarmique;
import model.joueur.Playable;

public class EtatPartie {
    private EchelleKarmique echelle;
    private Playable joueur1;
    private Playable joueur2;
    private PileCartes source;
    private PileCartes fosse;
    private int numTour;

    public EtatPartie(EchelleKarmique echelle, Playable joueur1, Playable joueur2, PileCartes source, PileCartes fosse, int numTour) {
        this.echelle = echelle;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.source = source;
        this.fosse = fosse;
        this.numTour = numTour;
    }
}
