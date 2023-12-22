/**
 * La classe {@code Crise} représente une carte du jeu avec le pouvoir spécifique de forcer
 * un adversaire à défausser l'une de ses œuvres.
 * Elle hérite de la classe abstraite {@link Carte}.
 */
package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Crise extends Carte {

    /**
     * Constructeur de la classe Crise.
     *
     * @param renderable L'objet permettant d'afficher des messages dans l'interface utilisateur.
     */
    public Crise(Renderable renderable) {
        super(renderable);
        this.nom = "Crise";
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Votre rival doit défausser une de ses œuvres";
    }

    /**
     * Méthode pour jouer le pouvoir de la carte Crise.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        Carte carteChoisie = null;

        // Vérifie si le joueur receveur a des œuvres exposées
        if (!joueurReceveur.getOeuvre().getCartes().isEmpty()) {
            // Si le joueur receveur n'est pas un ordinateur, permet au joueur de choisir la carte à défausser
            if (!(joueurReceveur instanceof Ordinateur)) {
                carteChoisie = this.renderer.choisirUneCarte(joueurReceveur.getOeuvre().getCartes());
            } else {
                // Si le joueur receveur est un ordinateur, choisit aléatoirement une carte à défausser
                Random r = new Random();
                carteChoisie = joueurReceveur.getOeuvre().getCartes().get(r.nextInt(joueurReceveur.getOeuvre().getCartes().size()));
            }
            // Supprime la carte choisie de la main du joueur receveur
            joueurReceveur.getOeuvre().getCartes().remove(carteChoisie);
        } else {
            this.renderer.displayErrorMessage("Impossible : Le rival n'a aucune œuvre exposée.");
        }
    }
}
