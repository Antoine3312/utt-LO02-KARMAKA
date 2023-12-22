package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;

/**
 * La classe ReveBrises représente une carte du jeu avec le pouvoir spécifique "Placez la première carte de la Vie Future de votre rival dans la vôtre".
 * Elle hérite de la classe Carte et implémente l'interface Renderable.
 */
public class ReveBrises extends Carte {

    /**
     * Constructeur de la classe ReveBrises.
     *
     * @param renderable L'objet qui gère l'affichage de la carte.
     */
    public ReveBrises(Renderable renderable) {
        super(renderable);
        this.nom = "ReveBrises"; // Nom de la carte
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
        this.intitulePouvoir = "Placez la première carte de la Vie Future de votre rival dans la vôtre"; // Description du pouvoir de la carte
    }

    /**
     * Méthode pour exécuter le pouvoir de la carte ReveBrises.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        if (!joueurReceveur.getVieFutur().getCartes().isEmpty()) {
            // Placer la première carte de la Vie Future du rival dans la Vie Future du joueur appelant
            joueurAppelant.getVieFutur().getCartes().push(
                    joueurReceveur.getVieFutur().getCartes().pop()
            );
        } else {
            this.renderer.displayErrorMessage("Impossible : la vie futur du rival n'a aucune carte.");
        }
    }
}
