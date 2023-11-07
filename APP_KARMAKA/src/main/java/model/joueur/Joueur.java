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

    public Joueur(String nom) {
        this.nom = nom;
    }

    public boolean isBot(){
        return false;
    }


    @Override
    public String toString() {
        return this.nom;
    }
}
