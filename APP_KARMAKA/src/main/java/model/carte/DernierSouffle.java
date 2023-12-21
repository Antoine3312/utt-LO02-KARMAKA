package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Carte "DernierSouffle" qui hérite de la classe abstraite "Carte".
 * Représente une carte permettant au joueur appelant de choisir un joueur cible qui devra défausser une carte de sa main.
 */
public abstract class DernierSouffle extends Carte {

    /**
     * Constructeur de la carte "DernierSouffle".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public DernierSouffle(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "DernierSouffle".
     * Le joueur appelant choisit un joueur cible qui doit défausser une carte de sa main.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur cible du pouvoir (non utilisé dans ce contexte).
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        Carte carteChoisie = null;
        if(!joueurReceveur.getMain().isEmpty()){
            if(!(joueurReceveur instanceof Ordinateur)) {
                carteChoisie = this.renderer.choisirUneCarte(joueurReceveur.getMain());
            } else {
                Random r = new Random();
                carteChoisie = joueurReceveur.getMain().get(r.nextInt(joueurReceveur.getMain().size()));
            }
            joueurReceveur.getMain().remove(carteChoisie);
        } else {
            this.renderer.displayErrorMessage("Le rival n'a aucune carte en main.");
        }
    }
}
