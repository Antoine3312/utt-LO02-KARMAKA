package model.carte;

import application.control.Renderable;
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
        this.point = 1;
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
        // Puisez 3 cartes à la Source
        joueurAppelant.puiserCartesSource(3);

        // Vous pouvez ensuite jouer une autre carte
        if (joueurAppelant instanceof Ordinateur) {
            // Si le joueur est un ordinateur, il choisit aléatoirement une carte à jouer
            jouerAutreCarte(joueurAppelant);
        } else {
            // Si le joueur est humain, il choisit une carte à jouer
            this.renderer.afficherCartes(joueurAppelant.getMain());
            Carte carteAJouer = this.renderer.choisirUneCarte(joueurAppelant.getMain());

            if (carteAJouer != null) {
                joueurAppelant.jouerCarte(carteAJouer);
            }
        }
    }

    /**
     * Méthode privée pour jouer une autre carte.
     * Si le joueur est un ordinateur, il choisit et joue automatiquement une autre carte.
     * Si le joueur est humain, il choisit et joue une autre carte via l'interface graphique.
     *
     * @param joueur Le joueur qui joue la carte.
     */
    private void jouerAutreCarte(Joueur joueur) {
        if (joueur instanceof Ordinateur) {
            // Si le joueur est un ordinateur, il choisit et joue automatiquement une autre carte
            jouerAutomatiquement(joueur);
        } else {
            // Si le joueur est humain, il choisit et joue une autre carte
            this.renderer.afficherCartes(joueur.getMain());
            Carte carteAJouer = this.renderer.choisirUneCarte(joueur.getMain());

            if (carteAJouer != null) {
                joueur.jouerCarte(carteAJouer);
            }
        }
    }

    /**
     * Méthode privée pour jouer automatiquement une carte (utilisée par l'ordinateur).
     * L'ordinateur choisit aléatoirement la première carte jouable dans sa main et la joue.
     *
     * @param joueur Le joueur qui joue la carte (ordinateur).
     */
    private void jouerAutomatiquement(Joueur joueur) {
        List<Carte> cartesJouables = joueur.getMain();

        if (!cartesJouables.isEmpty()) {
            Carte carteAJouer = cartesJouables.get(0); // Choix aléatoire de la première carte jouable
            joueur.jouerCarte(carteAJouer);
        }
    }
}
