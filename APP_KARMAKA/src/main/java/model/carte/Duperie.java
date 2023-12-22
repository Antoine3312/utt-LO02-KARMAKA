package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe abstraite représentant une carte de type "Duperie" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public class Duperie extends Carte {

    /**
     * Constructeur de la carte "Duperie".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Duperie(Renderable renderable) {
        super(renderable);
        this.nom = "Duperie";
        this.point = 3; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Duperie".
     * Le joueur appelant regarde 3 cartes de la Main d'un rival,
     * affiche les 3 premières cartes de la Main du rival, et choisit une carte à ajouter à sa Main.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Non utilisé dans ce contexte.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        if (joueurReceveur.getMain().size()<3){
            Carte carteChoisi = null;
            if (!(joueurAppelant instanceof Ordinateur)) {
                carteChoisi = this.renderer.choisirUneCarte(joueurReceveur.getMain().subList(0, 2));
            } else {
                Random r = new Random();
                carteChoisi = joueurReceveur.getMain().get(r.nextInt(joueurReceveur.getMain().size()));
            }
            joueurAppelant.getMain().add(carteChoisi);
        } else {
            this.renderer.displayErrorMessage("Impossible : Le joueur cible n'a pas assez de carte dans sa main (- de 3 cartes)");
        }
    }
}
