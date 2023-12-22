package model;

import model.carte.PileCartes;
import model.echelle.EchelleKarmique;
import model.joueur.Joueur;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class EtatPartie implements Serializable {

    private static final long serialVersionUID = 2711998155099132322L;
    private EchelleKarmique echelle;
    private Joueur joueur1;
    private Joueur joueur2;
    private PileCartes source;
    private PileCartes fosse;
    private int numTour;

    private static EtatPartie instance;

    private EtatPartie(){}

    public static EtatPartie getInstance(){
        if(instance == null){
            instance = new EtatPartie();
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

    public Joueur getJoueur(Joueur _j){
        List<Joueur> joueurs = Arrays.asList(joueur1, joueur2);
        for (Joueur j : joueurs){
            if(_j == j)
                return j;
        }
        return null;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public EchelleKarmique getEchelle() {
        return echelle;
    }

    public PileCartes getSource() {
        return source;
    }

    public PileCartes getFosse() {
        return fosse;
    }

    public int getNumTour() {
        return numTour;
    }

    public void setNumTour(int numTour) {
        this.numTour = numTour;
    }

    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }

    public static void setInstance(EtatPartie instance) {
        EtatPartie.instance = instance;
    }

    @Override
    public String toString() {
        return "EtatPartie{" +
                "echelle=" + echelle +
                ", joueur1=" + joueur1 +
                ", joueur2=" + joueur2 +
                ", source=" + source +
                ", fosse=" + fosse +
                ", numTour=" + numTour +
                '}';
    }
}
