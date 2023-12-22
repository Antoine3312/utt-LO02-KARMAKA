package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;

/**
 * Classe abstraite représentant la carte "ReveBrises" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public class ReveBrises extends Carte {

    /**
     * Constructeur de la carte "ReveBrises".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public ReveBrises(Renderable renderable) {
        super(renderable);
        this.nom = "ReveBrises";
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
        this.intitulePouvoir = "Placez la première carte de la Vie Future de votre rival dans la votre";
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "ReveBrises".
     * Transfère une carte de la Vie Futur du rival à la Vie Futur du joueur appelant.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur adverse dont la Vie Futur est affectée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        if(!joueurReceveur.getVieFutur().getCartes().isEmpty()){
            joueurAppelant.getVieFutur().getCartes().push(
                    joueurReceveur.getVieFutur().getCartes().pop()
            );
        } else {
            this.renderer.displayErrorMessage("Impossible : la vie futur du rival n'a aucune carte.");
        }
    }
}
