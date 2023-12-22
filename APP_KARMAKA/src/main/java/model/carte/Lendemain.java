/**
 * La classe {@code Lendemain} représente une carte du jeu avec le pouvoir spécifique de puiser
 * une carte à la Source et l'ajouter à la main du joueur qui a joué cette carte.
 * Elle hérite de la classe abstraite {@link Carte}.
 */
package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;


public class Lendemain extends Carte {

    /**
     * Constructeur de la classe Lendemain.
     *
     * @param renderable L'objet permettant d'afficher des messages dans l'interface utilisateur.
     */
    public Lendemain(Renderable renderable) {
        super(renderable);
        this.nom = "Lendemain";
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Puisez une carte à la Source";
    }

    /**
     * Méthode pour jouer le pouvoir de la carte Lendemain.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s", joueurAppelant.getNom(), this.getNom()));
        // Vérifie si la Source contient des cartes
        if (!EtatPartie.getInstance().getSource().getCartes().isEmpty()) {
            // Ajoute une carte à la main du joueur appelant depuis la Source
            joueurAppelant.getMain().add(EtatPartie.getInstance().getSource().getCartes().pop());
        } else {
            this.renderer.displayErrorMessage("Impossible : la source est vide.");
        }
    }
}
