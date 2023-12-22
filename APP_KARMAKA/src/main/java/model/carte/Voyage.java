package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;

/**
 * La classe Voyage représente une carte du jeu avec le pouvoir spécifique "Puisez 3 cartes à la Source".
 * Elle hérite de la classe Carte et implémente l'interface Renderable.
 */
public class Voyage extends Carte {

    /**
     * Constructeur de la classe Voyage.
     *
     * @param renderable L'objet qui gère l'affichage de la carte.
     */
    public Voyage(Renderable renderable) {
        super(renderable);
        this.nom = "Voyage"; // Nom de la carte
        this.point = 3; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Puisez 3 cartes à la Source"; // Description du pouvoir de la carte
    }

    /**
     * Méthode pour exécuter le pouvoir de la carte Voyage.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée (non utilisé dans ce cas).
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s", joueurAppelant.getNom(), this.getNom()));
        if (EtatPartie.getInstance().getFosse().getCartes().size() >= 3) {
            // Piocher 3 cartes de la Source et les ajouter à la main du joueur
            for (int i = 0; i < 3; i++) {
                joueurAppelant.getMain().add(EtatPartie.getInstance().getSource().getCartes().pop());
            }
        } else {
            this.renderer.displayErrorMessage("Impossible : Il y a moins de 3 cartes dans la fosse.");
        }
    }
}

