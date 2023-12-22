package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe abstraite représentant la carte "Crise" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class Crise extends Carte {

    /**
     * Constructeur de la carte "Crise".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Crise(Renderable renderable) {
        super(renderable);
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Crise".
     * Le rival de votre choix défausse une de ses Oeuvres.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur cible du pouvoir.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        Carte carteChoisie = null;
        if(!joueurReceveur.getOeuvre().getCartes().isEmpty()){
            if(!(joueurReceveur instanceof Ordinateur)) {
                carteChoisie = this.renderer.choisirUneCarte(joueurReceveur.getOeuvre().getCartes());
            } else {
                Random r = new Random();
                carteChoisie = joueurReceveur.getOeuvre().getCartes().get(r.nextInt(joueurReceveur.getOeuvre().getCartes().size()));
            }
            joueurReceveur.getOeuvre().getCartes().remove(carteChoisie);
        } else {
            this.renderer.displayErrorMessage("Le rival n'a aucune oeuvre exposée.");
        }

    }
}
