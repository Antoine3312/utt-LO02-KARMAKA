package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;

/**
 * Classe abstraite représentant la carte "Panique" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class Panique extends Carte {

    /**
     * Constructeur de la carte "Panique".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Panique(Renderable renderable) {
        super(renderable);
        this.nom = "Panique";
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Panique".
     * Si le joueur appelant est un ordinateur, il défausse automatiquement une carte du rival et joue
     * automatiquement une autre carte. Si le joueur appelant est humain, il choisit un joueur cible, défausse
     * la première carte de la Pile du joueur cible, puis peut jouer une autre carte.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur adverse ciblé par le pouvoir.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        if(!joueurReceveur.getPile().getCartes().isEmpty()){
            EtatPartie.getInstance().getFosse().getCartes().push(
                    joueurReceveur.getPile().getCartes().pop()
            );
        } else {
            this.renderer.displayErrorMessage("Impossible : la pile du rival est vide.");
        }
    }

}
