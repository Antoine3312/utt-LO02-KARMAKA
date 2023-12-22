package model.joueur;

import application.control.Renderable;
import model.EtatPartie;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.carte.PileCartes;
import model.echelle.Echellon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Joueur {
    protected String nom;
    private PileCartes oeuvre;
    private PileCartes pile;
    private PileCartes vieFutur;
    private List<Carte> main;
    private int nbAnneauxKarmique;
    private boolean hasWon = false;

    public Joueur(String nom) {
        this.nom = nom;
        this.oeuvre = new PileCartes();
        this.pile = new PileCartes();
        this.vieFutur = new PileCartes();
        this.main = new ArrayList<>();
        this.nbAnneauxKarmique = 0;
    }

    public Joueur(List<Carte> main, int jeton) {
        this.main = main;
        this.nbAnneauxKarmique = jeton;
    }

    public boolean isBot() {
        return false;
    }

    public PileCartes getPile() {
        return pile;
    }

    public List<Carte> getMain() {
        return main;
    }

    public int getNbAnneauxKarmique() {
        return nbAnneauxKarmique;
    }

    @Override
    public String toString() {
        return this.nom;
    }

    public String getNom() {
        return nom;
    }

    public PileCartes getOeuvre() {
        return oeuvre;
    }

    public PileCartes getVieFutur() {
        return vieFutur;
    }

    public void setMain(List<Carte> main) {
        this.main = main;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    public boolean hasWon() {
        return this.hasWon;
    }

    public void setNbAnneauxKarmique(int nbAnneauxKarmique) {
        this.nbAnneauxKarmique = nbAnneauxKarmique;
    }
}






