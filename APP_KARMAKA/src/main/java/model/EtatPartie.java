package model;

import model.carte.PileCartes;
import model.echelle.EchelleKarmique;
import model.joueur.Joueur;

public class EtatPartie {
    private EchelleKarmique echelle;
    private Joueur joueur1;
    private Joueur joueur2;
    private PileCartes source;
    private PileCartes fosse;
    private int numTour;

    private EtatPartie instance;

    private EtatPartie(){

    }

    public EtatPartie getInstance(){
        if(instance == null){
            return new EtatPartie();
        }
        return instance;
    }

    public void init(EchelleKarmique echelle, Joueur joueur1, Joueur joueur2, PileCartes source, PileCartes fosse, int numTour){
        this.echelle = echelle;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.source = source;
        this.fosse = fosse;
        this.numTour = numTour;
    }
}
