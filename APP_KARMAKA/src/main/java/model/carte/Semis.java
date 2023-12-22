package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;

/**
 * Classe abstraite représentant la carte "Semis" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class Semis extends Carte {

    /**
     * Constructeur de la carte "Semis".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Semis(Renderable renderable) {
        super(renderable);
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Semis".
     * Puisez 2 cartes à la Source et placez sur votre Vie Future 2 cartes de votre Main.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur adverse (non utilisé dans ce contexte).
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Puisez 2 cartes à la Source
        joueurAppelant.puiserCartesSource(2);

        // Placez sur votre Vie Future 2 cartes de votre Main
        List<Carte> cartesMain = joueurAppelant.getMain();

        // Vérifie s'il y a au moins 2 cartes dans la main
        if (cartesMain.size() >= 2) {
            for (int i = 0; i < 2; i++) {
                Carte carteAVieFuture = cartesMain.get(0); // Choix arbitraire de la première carte de la main
                joueurAppelant.getVieFutur().(carteAVieFuture);
                joueurAppelant.getMain().remove(carteAVieFuture);
            }
        }

        // Vous pouvez ensuite jouer une autre carte
        if (joueurAppelant instanceof Ordinateur) {
            jouerAutreCarte(joueurAppelant);
        } else {
            this.renderer.afficherCartes(joueurAppelant.getMain());
            Carte carteAJouer = this.renderer.choisirUneCarte(joueurAppelant.getMain());

            // Si le joueur a choisi une carte, il la joue
            if (carteAJouer != null) {
                joueurAppelant.jouerCarte(carteAJouer);
            }
        }
    }

    /**
     * Méthode pour jouer une autre carte.
     *
     * @param joueur Le joueur qui joue la carte.
     */
    private void jouerAutreCarte(Joueur joueur) {
        List<Carte> cartesJouables = joueur.getMain();

        // Si le joueur a des cartes jouables, il en choisit une et la joue
        if (!cartesJouables.isEmpty()) {
            Carte carteAJouer = cartesJouables.get(0); // Choix aléatoire de la première carte jouable
            joueur.jouerCarte(carteAJouer);
        }
    }
}

