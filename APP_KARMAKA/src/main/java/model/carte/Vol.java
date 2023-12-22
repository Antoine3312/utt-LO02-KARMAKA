package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;

/**
 * La classe Vol représente une carte du jeu avec le pouvoir spécifique "Placez dans votre main l'Oeuvre exposée d'un rival".
 * Elle hérite de la classe Carte et implémente l'interface Renderable.
 */
public class Vol extends Carte {

    /**
     * Constructeur de la classe Vol.
     *
     * @param renderable L'objet qui gère l'affichage de la carte.
     */
    public Vol(Renderable renderable) {
        super(renderable);
        this.nom = "Vol"; // Nom de la carte
        this.point = 3; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
        this.intitulePouvoir = "Placez dans votre main l'Oeuvre exposée d'un rival"; // Description du pouvoir de la carte
    }

    /**
     * Méthode pour exécuter le pouvoir de la carte Vol.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        if (!joueurReceveur.getVieFutur().getCartes().isEmpty()) {
            // Ajouter la première carte de l'Oeuvre Exposée du rival à la main du joueur
            joueurAppelant.getMain().add(joueurReceveur.getVieFutur().getCartes().pop());
        } else {
            this.renderer.displayErrorMessage("Impossible : Le rival n'a aucune oeuvre exposée.");
        }
    }
}

