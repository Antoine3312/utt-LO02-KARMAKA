package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;

import java.util.List;

/**
 * Classe abstraite représentant la carte "Longevite" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public class Longevite extends Carte {

    /**
     * Constructeur de la carte "Longevite".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Longevite(Renderable renderable) {
        super(renderable);
        this.nom = "Longevite";
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Longevite".
     * Placez 2 cartes puisées à la Source sur la Pile du joueur adverse.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur adverse ciblé par le pouvoir.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        if(!EtatPartie.getInstance().getSource().getCartes().isEmpty()){
            for (int i =0; i<2; i++) {
                Carte cartePiochee = EtatPartie.getInstance().getSource().getCartes().pop();
                joueurReceveur.getPile().getCartes().push(cartePiochee);
            }
        } else {
            this.renderer.displayErrorMessage("Impossible : la source est vide.");
        }

    }
}
