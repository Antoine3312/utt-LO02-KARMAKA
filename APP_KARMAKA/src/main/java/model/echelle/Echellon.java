package model.echelle;

import model.joueur.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Echellon {
    private List<Joueur> joueurs;
    private int ptsNecessairePourMonter;
    private NomPalier nom;

    public Echellon(int ptsNecessairePourMonter, NomPalier nom) {
        this.ptsNecessairePourMonter = ptsNecessairePourMonter;
        this.nom = nom;
        this.joueurs = new ArrayList<>();
    }

    public void addPlayers(List<Joueur> joueurs){
        this.joueurs.addAll(joueurs);
    }
}
