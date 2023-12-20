package model.carte;

import application.control.Renderable;
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
        Carte cartePiochee = null;
        List<Carte> carteSourceJoueurAppelant = joueurAppelant.getSource().getCartes();

        if (!carteSourceJoueurAppelant.isEmpty()) {
            // Piocher la première carte de la source du joueur appelant
            cartePiochee = carteSourceJoueurAppelant.get(0);

            // Retirer la carte de la Source du joueur appelant
            joueurAppelant.getSource().getCartes().remove(cartePiochee);

            // Ajouter la carte à la main du joueur appelant
            joueurAppelant.getMain().add(cartePiochee);

            System.out.println("Vous avez puisé la carte Lendemain de la Source.");

            // Permettre au joueur d'effectuer une autre action si nécessaire
            // (vous devrez implémenter la logique associée à cette action supplémentaire)
        } else {
            System.out.println("La Source est vide, vous ne pouvez pas piocher la carte Lendemain.");
        }
    }
}
