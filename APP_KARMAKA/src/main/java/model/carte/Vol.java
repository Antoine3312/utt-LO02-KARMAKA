package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;

/**
 * Classe abstraite représentant la carte "Vol" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class Vol extends Carte {

    /**
     * Constructeur de la carte "Vol".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Vol(Renderable renderable) {
        super(renderable);
        this.nom = "Vol";
        this.point = 3; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Vol".
     * Si le joueur appelant est un ordinateur, il choisit aléatoirement une carte exposée dans son œuvre.
     * Si le joueur est humain, il choisit une carte exposée en utilisant l'interface graphique.
     * La carte choisie est retirée de l'œuvre exposée et ajoutée à la main du joueur appelant.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur adverse.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        if(!joueurReceveur.getVieFutur().getCartes().isEmpty()){
            joueurAppelant.getMain().add(joueurReceveur.getVieFutur().getCartes().pop());
        } else {
            this.renderer.displayErrorMessage("Impossible : Le rival n'a aucune oeuvre exposée.");
        }
    }
}
