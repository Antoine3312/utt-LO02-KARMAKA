/**
 * La classe {@code Fournaise} représente une carte du jeu avec le pouvoir spécifique de défausser
 * les deux premières cartes de la Vie Future d'un adversaire.
 * Elle hérite de la classe abstraite {@link Carte}.
 */
package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;


public class Fournaise extends Carte {

    /**
     * Constructeur de la classe Fournaise.
     *
     * @param renderable L'objet permettant d'afficher des messages dans l'interface utilisateur.
     */
    public Fournaise(Renderable renderable) {
        super(renderable);
        this.nom = "Fournaise";
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Défaussez les 2 premières cartes de la Vie Future de votre Rival";
    }

    /**
     * Méthode pour jouer le pouvoir de la carte Fournaise.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        // Vérifie si le joueur cible a au moins deux cartes dans sa Vie Future
        if (joueurReceveur.getVieFutur().getCartes().size() >= 2) {
            // Défausse les deux premières cartes de la Vie Future du joueur cible
            for (int i = 0; i < 2; i++) {
                Carte carteADefosser = joueurReceveur.getVieFutur().getCartes().pop();
                EtatPartie.getInstance().getFosse().getCartes().add(carteADefosser);
            }
        } else {
            this.renderer.displayErrorMessage("Impossible : Le rival n'a pas assez de cartes dans sa Vie Future.");
        }
    }
}

