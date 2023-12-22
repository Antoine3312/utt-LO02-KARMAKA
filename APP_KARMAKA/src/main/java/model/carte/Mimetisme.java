package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

/**
 * Classe abstraite représentant la carte "Mimétisme" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 *
 * @param <Oeuvre> Le type d'objet représentant une œuvre (à adapter en fonction de la conception du jeu).
 */
public class Mimetisme extends Carte {

    /**
     * Constructeur de la carte "Mimétisme".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Mimetisme(Renderable renderable) {
        super(renderable);
        this.nom = "Mimetisme";
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.MOSAIQUE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Mimétisme".
     * Choisissez un Rival et copiez le pouvoir de son Oeuvre Exposée.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur adverse ciblé par le pouvoir.
     */
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        if(!joueurReceveur.getOeuvre().getCartes().isEmpty()){
            joueurReceveur.getOeuvre().getCartes().peek().jouerPouvoir(joueurAppelant, joueurReceveur);
        } else {
            this.renderer.displayErrorMessage("L'adversaire n'a aucune oeuvre de posé.");
        }
    }
}
