package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;

// Carte "Voyage" qui hérite de la classe abstraite "Carte"
public abstract class Voyage extends Carte {

    // Constructeur de la carte "Voyage"
    public Voyage(Renderable renderable) {
        super(renderable);
        this.point = 1;
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
    }

    // Implémentation de la méthode jouerPouvoir() définie dans l'interface PouvoirCarte
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

    // Méthode pour jouer une autre carte
    private void jouerAutreCarte(Joueur joueur) {

        if (joueur instanceof Ordinateur) {
            // Si le joueur est un ordinateur, il choisit et joue automatiquement une autre carte
            jouerAutomatiquement(joueur);
        } else {
            // Si le joueur est humain, il choisit et joue une autre carte
            this.renderer.afficherCartes(joueur.getMain().getCartes());
            Carte carteAJouer = this.renderer.choisirUneCarte(joueur.getMain().getCartes());

            if (carteAJouer != null) {
                joueur.jouerCarte(carteAJouer);
            }
        }
    }

    // Méthode pour jouer automatiquement une carte (utilisée par l'ordinateur)
    private void jouerAutomatiquement(Joueur joueur) {

        List<Carte> cartesJouables = joueur.getMain().getCartesJouables();

        if (!cartesJouables.isEmpty()) {
            Carte carteAJouer = cartesJouables.get(0); // Choix aléatoire de la première carte jouable
            joueur.jouerCarte(carteAJouer);
        }
    }
}
