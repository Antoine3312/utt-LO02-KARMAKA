package model;

import model.carte.PileCartes;
import model.echelle.EchelleKarmique;
import model.joueur.Joueur;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * La classe EtatPartie représente l'état d'une partie de jeu.
 * Elle implémente Serializable pour permettre la sérialisation.
 */
public class EtatPartie implements Serializable {

    private static final long serialVersionUID = 2711998155099132322L;
    private EchelleKarmique echelle;  // L'échelle karmique de la partie.
    private Joueur joueur1;  // Le premier joueur.
    private Joueur joueur2;  // Le deuxième joueur.
    private PileCartes source;  // La pile de cartes source.
    private PileCartes fosse;  // La fosse de cartes.
    private int numTour;  // Le numéro du tour.

    private static EtatPartie instance;  // Instance unique de la classe.

    // Constructeur privé pour assurer une seule instance de la classe.
    private EtatPartie(){}

    /**
     * Méthode pour obtenir l'instance unique de la classe EtatPartie.
     * Si l'instance n'existe pas, elle est créée.
     *
     * @return L'instance unique de la classe EtatPartie.
     */
    public static EtatPartie getInstance(){
        if(instance == null){
            instance = new EtatPartie();
        }
        return instance;
    }

    /**
     * Méthode pour initialiser l'état de la partie.
     *
     * @param echelle  L'échelle karmique de la partie.
     * @param joueur1  Le premier joueur.
     * @param joueur2  Le deuxième joueur.
     * @param source   La pile de cartes source.
     * @param fosse    La fosse de cartes.
     * @param numTour  Le numéro du tour.
     */
    public void init(EchelleKarmique echelle, Joueur joueur1, Joueur joueur2, PileCartes source, PileCartes fosse, int numTour){
        this.echelle = echelle;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.source = source;
        this.fosse = fosse;
        this.numTour = numTour;
    }

    /**
     * Méthode pour obtenir le joueur correspondant à l'instance passée en paramètre.
     *
     * @param _j Le joueur dont on veut obtenir l'instance correspondante.
     * @return Le joueur correspondant à l'instance passée en paramètre.
     */
    public Joueur getJoueur(Joueur _j){
        List<Joueur> joueurs = Arrays.asList(joueur1, joueur2);
        for (Joueur j : joueurs){
            if(_j == j)
                return j;
        }
        return null;
    }

    // Getters et setters pour les différentes propriétés.

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

    /**
     * Méthode pour définir l'instance de la classe EtatPartie à partir d'une autre instance.
     *
     * @param instance L'instance à partir de laquelle initialiser l'état de la partie.
     */
    public void setInstance(EtatPartie instance) {
        this.joueur1 = instance.getJoueur1();
        this.joueur2 = instance.getJoueur2();
        this.echelle = instance.getEchelle();
        this.source = instance.getSource();
        this.fosse = instance.getFosse();
        this.numTour = instance.getNumTour();
    }

    /**
     * Méthode pour obtenir une représentation textuelle de l'objet EtatPartie.
     *
     * @return Une chaîne représentant l'état de la partie.
     */
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
