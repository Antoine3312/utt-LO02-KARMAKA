package model.carte;

import application.control.Renderable;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.Collections;
import java.util.List;

/**
 * Classe abstraite représentant la carte "Coup d'Œil" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class CoupDOeil extends Carte {

    /**
     * Constructeur de la carte "Coup d'Œil".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public CoupDOeil(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Coup d'Œil".
     * Regardez la Main d'un rival. Vous pouvez ensuite jouer une autre carte.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur cible du pouvoir.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        if (!(joueurAppelant instanceof Ordinateur)){
            this.renderer.afficherCartes(joueurReceveur.getMain());
        }
    }

}
