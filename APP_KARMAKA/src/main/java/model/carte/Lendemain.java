package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;

import java.util.List;

/**
 * Classe abstraite représentant la carte "Lendemain" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class Lendemain extends Carte {

    /**
     * Constructeur de la carte "Lendemain".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Lendemain(Renderable renderable) {
        super(renderable);
        this.nom = "Lendemain";
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Lendemain".
     * Pioche la première carte de la Source du joueur appelant et l'ajoute à sa main.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur cible du pouvoir (non utilisé dans ce cas).
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s", joueurAppelant.getNom(), this.getNom()));
        if(!EtatPartie.getInstance().getSource().getCartes().isEmpty()){
            joueurAppelant.getMain().add(EtatPartie.getInstance().getSource().getCartes().pop());
        } else {
            this.renderer.displayErrorMessage("Impossible : la source est vide.");
        }

    }
}
