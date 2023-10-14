package model.joueur;

import model.carte.Carte;
import model.carte.PileCartes;

import java.util.List;

public class Joueur {
    private String nom;
    private PileCartes oeuvre;
    private PileCartes pile;
    private PileCartes vieFutur;
    private List<Carte> main;

    protected void piocher(){
        System.out.println("Je pioche ...");
    }

}
