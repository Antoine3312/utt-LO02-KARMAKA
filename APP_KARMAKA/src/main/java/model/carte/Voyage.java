package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;

/**
 * Classe abstraite représentant la carte "Voyage" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class Voyage extends Carte {

    /**
     * Constructeur de la carte "Voyage".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Voyage(Renderable renderable) {
        super(renderable);
        this.nom = "Voyage";
        this.point = 3;
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Voyage".
     * Puise 3 cartes à la Source et permet au joueur d'en jouer une autre.
     * Si le joueur est un ordinateur, il choisit et joue automatiquement une autre carte.
     * Si le joueur est humain, il choisit une carte à jouer via l'interface graphique.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur adverse (non utilisé dans cette carte).
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s", joueurAppelant.getNom(), this.getNom()));
        if(EtatPartie.getInstance().getFosse().getCartes().size()>=3) {
            for (int i =0; i<3; i++){
                joueurAppelant.getMain().add(EtatPartie.getInstance().getSource().getCartes().pop());
            }
        } else {
            this.renderer.displayErrorMessage("Impossible : Il y a moins de 3 carte dans la fosse.");
        }
    }
}
