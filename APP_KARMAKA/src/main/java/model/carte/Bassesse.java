package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Classe abstraite représentant une carte de type "Bassesse". Cette classe hérite de la classe abstraite "Carte".
 */
public class Bassesse extends Carte {

    /**
     * Constructeur de la carte "Bassesse".
     *
     * @param renderable L'objet renderable associé à la carte.
     */
    public Bassesse(Renderable renderable) {
        super(renderable);
        this.nom = "Bassesse";
        this.point = 3; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
    }

    /**
     * Implémentation de la méthode jouerPouvoir() définie dans l'interface PouvoirCarte.
     *
     * @param joueurAppelant Le joueur qui utilise le pouvoir de la carte.
     * @param joueurReceveur Le joueur ciblé par le pouvoir de la carte.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        // Défaussez au hasard 2 cartes de la Main d'un rival
        this.defausserAuHasard(joueurReceveur, 2);
    }

    private void defausserAuHasard(Joueur joueur, int nombreCartes) {
        List<Carte> cartesMain = joueur.getMain();

        // Vérification si le nombre de cartes à défausser est inférieur ou égal au nombre de cartes en main
        if (nombreCartes <= cartesMain.size()) {
            // Défausse aléatoire de cartes
            Collections.shuffle(cartesMain);
            List<Carte> cartesADefausser = cartesMain.subList(0, nombreCartes);

            // Ajout des cartes à la défausse et suppression de la main
            EtatPartie.getInstance().getFosse().addCartes(cartesADefausser);
            cartesMain.removeAll(cartesADefausser);

        }else{
            this.renderer.displayErrorMessage("Impossible : Le rival n'a pas assez de cartes en main");
        }

    }
}
