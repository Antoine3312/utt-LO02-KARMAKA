package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;

/**
 * La classe Mimetisme représente une carte du jeu avec le pouvoir spécifique "Utilisez le pouvoir de l'Oeuvre Exposée de votre rival".
 * Elle hérite de la classe Carte et implémente l'interface Renderable.
 */
public class Mimetisme extends Carte {

    /**
     * Constructeur de la classe Mimetisme.
     *
     * @param renderable L'objet qui gère l'affichage de la carte.
     */
    public Mimetisme(Renderable renderable) {
        super(renderable);
        this.nom = "Mimetisme"; // Nom de la carte
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.MOSAIQUE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Utilisez le pouvoir de l'Oeuvre Exposée de votre rival"; // Description du pouvoir de la carte
    }

    /**
     * Méthode pour exécuter le pouvoir de la carte Mimetisme.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        if (!joueurReceveur.getOeuvre().getCartes().isEmpty()) {
            // Utiliser le pouvoir de l'Oeuvre Exposée de l'adversaire
            joueurReceveur.getOeuvre().getCartes().peek().jouerPouvoir(joueurAppelant, joueurReceveur);
        } else {
            this.renderer.displayErrorMessage("L'adversaire n'a aucune oeuvre posée.");
        }
    }
}
