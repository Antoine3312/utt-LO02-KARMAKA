package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

/**
 * Classe abstraite représentant la carte "Vengeance" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public class Vengeance extends Carte {

    /**
     * Constructeur de la carte "Vengeance".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Vengeance(Renderable renderable) {
        super(renderable);
        this.nom = "Vengeance";
        this.point = 3; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Défaussez l'Oeuvre Exposée de votre rival";
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Vengeance".
     * Si le joueur appelant est un ordinateur, il défausse automatiquement l'œuvre exposée de l'autre joueur.
     * Si le joueur est humain, il défausse l'œuvre exposée de l'autre joueur en utilisant l'interface graphique.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur adverse.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        if(!joueurReceveur.getVieFutur().getCartes().isEmpty()){
            EtatPartie.getInstance().getFosse().getCartes().push(joueurReceveur.getVieFutur().getCartes().pop());
        } else {
            this.renderer.displayErrorMessage("Impossible : Le rival n'a aucune oeuvre exposée.");
        }
    }
}
