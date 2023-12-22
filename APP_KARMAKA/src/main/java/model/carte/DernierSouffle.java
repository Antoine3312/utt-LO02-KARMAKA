package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.Random;

/**
 * Carte "Fournaise" qui hérite de la classe abstraite "Carte".
 * Représente une carte permettant au joueur appelant de défausser les 2 premières cartes de la Vie Future d'un rival.
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
     * Le joueur appelant défausse les 2 premières cartes de la Vie Future d'un rival.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur cible du pouvoir.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        int nombreCartesADefausser = 2;

        // Vérifie si le joueur cible a au moins 2 cartes dans sa Vie Future
        if (joueurReceveur.getVieFuture().size() >= nombreCartesADefausser) {
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
