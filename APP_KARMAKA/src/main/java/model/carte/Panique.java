package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;

/**
 * La classe Panique représente une carte du jeu avec le pouvoir spécifique "Défaussez la première carte de la Pile de votre rival".
 * Elle hérite de la classe Carte et implémente l'interface Renderable.
 */
public class Panique extends Carte {

    /**
     * Constructeur de la classe Panique.
     *
     * @param renderable L'objet qui gère l'affichage de la carte.
     */
    public Panique(Renderable renderable) {
        super(renderable);
        this.nom = "Panique"; // Nom de la carte
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Défaussez la première carte de la Pile de votre rival"; // Description du pouvoir de la carte
    }

    /**
     * Méthode pour exécuter le pouvoir de la carte Panique.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        if (!joueurReceveur.getPile().getCartes().isEmpty()) {
            // Défausser la première carte de la pile du rival et la placer dans la fosse
            EtatPartie.getInstance().getFosse().getCartes().push(
                    joueurReceveur.getPile().getCartes().pop()
            );
        } else {
            this.renderer.displayErrorMessage("Impossible : la pile du rival est vide.");
        }
    }

}
