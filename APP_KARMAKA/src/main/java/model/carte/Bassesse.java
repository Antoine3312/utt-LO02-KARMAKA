/**
 * La classe {@code Bassesse} représente une carte du jeu avec le pouvoir spécifique de défausser
 * au hasard deux cartes de la main d'un adversaire.
 * Elle hérite de la classe abstraite {@link Carte}.
 */
package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Bassesse extends Carte {

    /**
     * Constructeur de la classe Bassesse.
     *
     * @param renderable L'objet permettant d'afficher des messages dans l'interface utilisateur.
     */
    public Bassesse(Renderable renderable) {
        super(renderable);
        this.nom = "Bassesse";
        this.point = 3; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Défaussez au hasard 2 cartes de la Main de votre rival";
    }

    /**
     * Méthode pour jouer le pouvoir de la carte Bassesse.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        // Défaussez au hasard 2 cartes de la Main d'un rival
        this.defausserAuHasard(joueurReceveur, 2);
    }

    /**
     * Méthode privée pour défausser au hasard un certain nombre de cartes d'une main.
     *
     * @param joueur       Le joueur dont les cartes seront défaussées.
     * @param nombreCartes Le nombre de cartes à défausser.
     */
    private void defausserAuHasard(Joueur joueur, int nombreCartes) {
        List<Carte> cartesMain = joueur.getMain();

        // Vérification si le nombre de cartes à défausser est inférieur ou égal au nombre de cartes en main
        if (nombreCartes <= cartesMain.size()) {
            // Défausse aléatoire de cartes
            Collections.shuffle(cartesMain);
            List<Carte> cartesADefausser = cartesMain.subList(0, nombreCartes);

            // Ajout des cartes à la défausse et suppression de la main
            EtatPartie.getInstance().getFosse().getCartes().addAll(cartesADefausser);
            cartesMain.removeAll(cartesADefausser);

        } else {
            // Affichage d'un message d'erreur si le rival n'a pas assez de cartes en main
            this.renderer.displayErrorMessage("Impossible : Le rival n'a pas assez de cartes en main");
        }
    }
}

