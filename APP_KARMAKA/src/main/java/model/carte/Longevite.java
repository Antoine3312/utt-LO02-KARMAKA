package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;

import java.util.List;

/**
 * La classe Longevite représente une carte du jeu avec le pouvoir spécifique "Placez 2 cartes puisées à la Source sur votre Source".
 * Elle hérite de la classe Carte et implémente l'interface Renderable.
 */
public class Longevite extends Carte {

    /**
     * Constructeur de la classe Longevite.
     *
     * @param renderable L'objet qui gère l'affichage de la carte.
     */
    public Longevite(Renderable renderable) {
        super(renderable);
        this.nom = "Longevite"; // Nom de la carte
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Placez 2 cartes puisées à la Source sur votre Source"; // Description du pouvoir de la carte
    }

    /**
     * Méthode pour exécuter le pouvoir de la carte Longevite.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        if (!EtatPartie.getInstance().getSource().getCartes().isEmpty()) {
            // Récupérer 2 cartes de la Source et les placer sur la Source du joueur receveur
            for (int i = 0; i < 2; i++) {
                Carte cartePiochee = EtatPartie.getInstance().getSource().getCartes().pop();
                joueurReceveur.getPile().getCartes().push(cartePiochee);
            }
        } else {
            this.renderer.displayErrorMessage("Impossible : la source est vide.");
        }
    }
}

