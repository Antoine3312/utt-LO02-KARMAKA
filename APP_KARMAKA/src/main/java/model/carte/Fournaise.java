package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;

import java.util.List;

/**
 * Classe abstraite représentant une carte de type "Fournaise" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class Fournaise extends Carte {

    /**
     * Constructeur de la carte "Fournaise".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Fournaise(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Fournaise".
     * Défausse les 2 premières cartes de la Vie Future d'un rival.
     *
     * @param joueurAppelant Non utilisé dans ce contexte.
     * @param joueurReceveur Le joueur dont la Vie Future est affectée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Défaussez les 2 premières cartes de la Vie Future d'un rival
        List<Carte> cartesVieFuturRival = joueurReceveur.getVieFutur().getCartes();

        // Défausser les 2 premières cartes s'il y en a au moins 2
        if (cartesVieFuturRival.size() >= 2) {
            List<Carte> cartesADefausser = cartesVieFuturRival.subList(0, 2);

            // Défausser les cartes choisies
            joueurReceveur.getFosse().addCartes(cartesADefausser);
            joueurReceveur.getVieFutur().removeCartes(cartesADefausser);
        }
    }
}
