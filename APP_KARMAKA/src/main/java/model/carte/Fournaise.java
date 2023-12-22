package model.carte;

import application.control.Renderable;
import model.EtatPartie;
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
        this.nom = "Fournaise";
        this.point = 2; // Définition du nombre de points attribués par cette carte
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
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        if (joueurReceveur.getVieFutur().getCartes().size() >= 2) {
            for (int i = 0; i<2; i++){
                Carte carteADefosser = joueurReceveur.getVieFutur().getCartes().pop();
                EtatPartie.getInstance().getFosse().getCartes().add(carteADefosser);
            }
        } else {
            this.renderer.displayErrorMessage("Impossible : Le rival n'a pas assez de cartes dans sa Vie Future.");
        }
    }
}

