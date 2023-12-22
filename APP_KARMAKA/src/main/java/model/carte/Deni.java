/**
 * La classe {@code Deni} représente une carte du jeu avec le pouvoir spécifique de défausser
 * une carte de sa main et d'utiliser le pouvoir de cette carte.
 * Elle hérite de la classe abstraite {@link Carte}.
 */
package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Deni extends Carte {

    /**
     * Constructeur de la classe Deni.
     *
     * @param renderable L'objet permettant d'afficher des messages dans l'interface utilisateur.
     */
    public Deni(Renderable renderable) {
        super(renderable);
        this.nom = "Deni";
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
        this.intitulePouvoir = "Défaussez une carte de votre Main et utilisez son pouvoir";
    }

    /**
     * Méthode pour jouer le pouvoir de la carte Deni.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s", joueurAppelant.getNom(), this.getNom()));
        // Le joueur appelant défausse une carte de sa main
        Carte carteDefausser = defausserCarte(joueurAppelant);

        // Copiez le pouvoir de la carte défaussée
        copierPouvoirCarte(carteDefausser, joueurAppelant, joueurReceveur);
    }

    /**
     * Méthode privée pour défausser une carte de la main du joueur.
     *
     * @param joueur Le joueur qui défausse la carte.
     * @return La carte défaussée.
     */
    private Carte defausserCarte(Joueur joueur) {
        Carte carteChoisi = null;
        // Vérifie si la main du joueur n'est pas vide
        if (!joueur.getMain().isEmpty()) {
            // Si le joueur n'est pas un ordinateur, permet au joueur de choisir la carte à défausser
            if (!(joueur instanceof Ordinateur)) {
                carteChoisi = this.renderer.choisirUneCarte(joueur.getMain());
            } else {
                // Si le joueur est un ordinateur, choisit aléatoirement une carte à défausser
                Random r = new Random();
                carteChoisi = joueur.getMain().get(r.nextInt(joueur.getMain().size()));
            }
            // Défausser la carte choisie par le joueur
            EtatPartie.getInstance().getFosse().getCartes().add(carteChoisi);
            joueur.getMain().remove(carteChoisi);
        } else {
            this.renderer.displayErrorMessage("Impossible : Votre main est vide.");
        }
        return carteChoisi;
    }

    /**
     * Méthode privée pour copier le pouvoir de la carte défaussée.
     *
     * @param carteDefausser La carte dont le pouvoir doit être copié.
     * @param joueurAppelant Le joueur qui utilise le pouvoir.
     * @param joueurReceveur Le joueur sur lequel le pouvoir est appliqué.
     */
    private void copierPouvoirCarte(Carte carteDefausser, Joueur joueurAppelant, Joueur joueurReceveur) {
        // Logique pour copier le pouvoir de la carte défaussée (appliquer son pouvoir)
        if (carteDefausser != null) {
            carteDefausser.jouerPouvoir(joueurAppelant, joueurReceveur);
        }
    }
}
