package model.joueur;

import model.carte.Carte;
import model.carte.PileCartes;

import java.util.List;

public class Joueur {
    protected String nom;
    private PileCartes oeuvre;
    private PileCartes pile;
    private PileCartes vieFutur;
    private List<Carte> main;

    private boolean hasWon = false;
    public Joueur(String nom) {
        this.nom = nom;
    }

    public boolean isBot(){
        return false;
    }

    public PileCartes getPile() {
        return pile;
    }

    public List<Carte> getMain() {
        return main;
    }

    @Override
    public String toString() {
        return this.nom;
    }

    public boolean hasWon() {
        return this.hasWon;
    }
}
