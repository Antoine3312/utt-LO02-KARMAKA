package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;

import java.util.List;

/**
 * Classe abstraite représentant la carte "Longevite" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class Longevite extends Carte {

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
        // Placez 2 cartes puisées à la Source sur la Pile du joueur adverse
        int nombreCartesAPuiser = 2;

        // Puisez 2 cartes à la Source
        int cartesPuisées = joueurAppelant.puiserCartesSource(nombreCartesAPuiser);

        // Placez les cartes sur la Pile du joueur adverse (joueurReceveur)
        List<Carte> cartesPuisees = joueurAppelant.getSource().getDernieresCartesPuisees(cartesPuisées);
        joueurReceveur.getPile().addCartes(cartesPuisees);
    }
}
