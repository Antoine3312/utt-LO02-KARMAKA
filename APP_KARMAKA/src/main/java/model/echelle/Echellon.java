package model.echelle;

import model.joueur.Playable;

import java.util.List;

public class Echellon {
    private List<Playable> joueurs;
    private int ptsNecessairePourMonter;
    private NomPalier nom;

    public Echellon(int ptsNecessairePourMonter, NomPalier nom) {
        this.ptsNecessairePourMonter = ptsNecessairePourMonter;
        this.nom = nom;
    }
}
