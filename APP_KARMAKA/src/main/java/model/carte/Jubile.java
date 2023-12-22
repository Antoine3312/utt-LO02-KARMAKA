/**
 * La classe {@code Jubile} représente une carte du jeu avec le pouvoir spécifique de placer
 * deux cartes de sa main sur ses propres œuvres.
 * Elle hérite de la classe abstraite {@link Carte}.
 */
package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;


public class Jubile extends Carte {

    /**
     * Constructeur de la classe Jubile.
     *
     * @param renderable L'objet permettant d'afficher des messages dans l'interface utilisateur.
     */
    public Jubile(Renderable renderable) {
        super(renderable);
        this.nom = "Jubile";
        this.point = 3; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Placez 2 cartes de votre Main sur vos Œuvres";
    }

    /**
     * Méthode pour jouer le pouvoir de la carte Jubile.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        // Vérifie si le joueur appelant a au moins deux cartes dans sa main
        if (joueurAppelant.getMain().size() >= 2) {
            // Si le joueur appelant est un joueur humain, lui permet de choisir deux cartes de sa main
            if (!(joueurAppelant instanceof Ordinateur)) {
                List<Carte> cartesChoisies = this.renderer.choisirDeuxCarte(joueurAppelant.getMain());
                joueurAppelant.getMain().removeAll(cartesChoisies);
                joueurAppelant.getOeuvre().getCartes().addAll(cartesChoisies);
            } else {
                // Si le joueur appelant est un ordinateur, choisit aléatoirement deux cartes de sa main
                Random r = new Random();
                for (int i = 0; i < 2; i++) {
                    Carte carteChoisi = joueurAppelant.getMain().get(r.nextInt(joueurAppelant.getMain().size()));
                    joueurAppelant.getOeuvre().getCartes().push(carteChoisi);
                    joueurAppelant.getMain().remove(carteChoisi);
                }
            }
        } else {
            this.renderer.displayErrorMessage("Impossible : Vous n'avez pas assez de cartes dans votre main.");
        }
    }
}
