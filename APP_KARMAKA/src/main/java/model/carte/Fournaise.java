package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;

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
        int nombreCartesADefausser = 2;

        // Vérifie si le joueur cible a au moins 2 cartes dans sa Vie Future
        if (joueurReceveur.carteVieFut().size() >= nombreCartesADefausser) {
            // Défaussez les 2 premières cartes de la Vie Future du joueur cible
            for (int i = 0; i < nombreCartesADefausser; i++) {
                Carte carteChoisie = choisirCarteADefausser(joueurReceveur);
                joueurReceveur.getVieFuture().remove(carteChoisie);
            }
        } else {
            this.renderer.displayErrorMessage("Le rival n'a pas assez de cartes dans sa Vie Future.");
        }
    }

    /**
     * Méthode pour choisir une carte à défausser parmi les cartes de la Vie Future d'un joueur.
     *
     * @param joueur Le joueur cible.
     * @return La carte choisie à défausser.
     */
    private Carte choisirCarteADefausser(Joueur joueur) {
        Carte carteChoisie = null;

        // Vérifie si le joueur cible est un ordinateur
        if (joueur instanceof Ordinateur) {
            // Si le joueur est un ordinateur, choisissez une carte de manière aléatoire
            Random r = new Random();
            carteChoisie = joueur.getVieFuture().get(r.nextInt(joueur.getVieFuture().size()));
        } else {
            // Si le joueur n'est pas un ordinateur, utilisez l'interface Renderer pour choisir une carte à défausser
            carteChoisie = this.renderer.choisirUneCarte(joueur.getVieFuture());
        }

        return carteChoisie;
    }
}
}
