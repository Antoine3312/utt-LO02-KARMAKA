package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;

import java.util.List;

/**
 * Classe abstraite représentant la carte "Incarnation" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class Incarnation extends Carte {

    /**
     * Constructeur de la carte "Incarnation".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Incarnation(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.MOSAIQUE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Incarnation".
     * Choisissez une de vos œuvres. Copiez son pouvoir.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur cible du pouvoir.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Choisissez une de vos œuvres
        List<Carte> oeuvresExposees = joueurAppelant.getOeuvresExposee();

        if (!oeuvresExposees.isEmpty()) {
            this.renderer.afficherCartes(oeuvresExposees);
            Carte oeuvreChoisie = this.renderer.choisirUneCarte(oeuvresExposees);

            if (oeuvreChoisie != null) {
                // Copiez son pouvoir
                PouvoirCarte pouvoirCopie = oeuvreChoisie.getPouvoir();

                if (pouvoirCopie != null) {
                    pouvoirCopie.appliquerPouvoir(joueurAppelant, joueurReceveur);
                }
            }
        }
    }
}
