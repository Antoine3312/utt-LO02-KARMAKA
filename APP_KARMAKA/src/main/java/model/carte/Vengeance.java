package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

/**
 * La classe Vengeance représente une carte du jeu avec le pouvoir spécifique "Défaussez l'Oeuvre Exposée de votre rival".
 * Elle hérite de la classe Carte et implémente l'interface Renderable.
 */
public class Vengeance extends Carte {

    /**
     * Constructeur de la classe Vengeance.
     *
     * @param renderable L'objet qui gère l'affichage de la carte.
     */
    public Vengeance(Renderable renderable) {
        super(renderable);
        this.nom = "Vengeance"; // Nom de la carte
        this.point = 3; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Défaussez l'Oeuvre Exposée de votre rival"; // Description du pouvoir de la carte
    }

    /**
     * Méthode pour exécuter le pouvoir de la carte Vengeance.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        if (!joueurReceveur.getVieFutur().getCartes().isEmpty()) {
            // Défausser la première carte de l'Oeuvre Exposée du rival et la placer dans la Fosse
            EtatPartie.getInstance().getFosse().getCartes().push(joueurReceveur.getVieFutur().getCartes().pop());
        } else {
            this.renderer.displayErrorMessage("Impossible : Le rival n'a aucune oeuvre exposée.");
        }
    }
}
