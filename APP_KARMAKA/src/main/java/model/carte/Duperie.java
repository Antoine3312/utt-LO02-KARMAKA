/**
 * La classe {@code Duperie} représente une carte du jeu avec le pouvoir spécifique de regarder
 * trois cartes de la main d'un adversaire, puis d'ajouter l'une d'entre elles à sa propre main.
 * Elle hérite de la classe abstraite {@link Carte}.
 */
package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Duperie extends Carte {

    /**
     * Constructeur de la classe Duperie.
     *
     * @param renderable L'objet permettant d'afficher des messages dans l'interface utilisateur.
     */
    public Duperie(Renderable renderable) {
        super(renderable);
        this.nom = "Duperie";
        this.point = 3; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
        this.intitulePouvoir = "Regardez 3 cartes de la Main de votre rival, et ajoutez-en une à votre Main";
    }

    /**
     * Méthode pour jouer le pouvoir de la carte Duperie.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        // Vérifie si le joueur cible a moins de 3 cartes en main
        if (joueurReceveur.getMain().size() < 3) {
            Carte carteChoisi = null;
            // Si le joueur appelant n'est pas un ordinateur, permet au joueur de choisir une carte parmi les deux premières
            if (!(joueurAppelant instanceof Ordinateur)) {
                carteChoisi = this.renderer.choisirUneCarte(joueurReceveur.getMain().subList(0, 2));
            } else {
                // Si le joueur appelant est un ordinateur, choisit aléatoirement une carte parmi les deux premières
                Random r = new Random();
                carteChoisi = joueurReceveur.getMain().get(r.nextInt(joueurReceveur.getMain().size()));
            }
            // Ajoute la carte choisie à la main du joueur appelant
            joueurAppelant.getMain().add(carteChoisi);
        } else {
            this.renderer.displayErrorMessage("Impossible : Le joueur cible n'a pas assez de carte dans sa Main (- de 3 cartes)");
        }
    }
}
