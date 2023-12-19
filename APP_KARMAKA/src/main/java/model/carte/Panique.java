package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;

// Carte "Panique" qui hérite de la classe abstraite "Carte"
public abstract class Panique extends Carte {

    // Constructeur de la carte "Panique"
    public Panique(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
    }

    // Implémentation de la méthode jouerPouvoir() définie dans l'interface PouvoirCarte
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        if (joueurAppelant instanceof Ordinateur) {
            // Si le joueur appelant est un ordinateur, il défausse automatiquement une carte du rival
            defausserAutomatiquement(joueurReceveur);
            // Ensuite, l'ordinateur joue automatiquement une autre carte
            jouerAutreCarte(joueurAppelant);
        } else {
            // Si le joueur appelant est humain, il choisit un joueur cible
            Joueur joueurCible = choisirJoueurCible();

            if (joueurCible != null) {
                // Défausse la première carte de la Pile du joueur cible
                defausserPileJoueur(joueurCible);

                // Ensuite, le joueur peut jouer une autre carte
                jouerAutreCarte(joueurAppelant);
            }
        }
    }

    // Méthode pour choisir un joueur cible
    private Joueur choisirJoueurCible() {
        // Logique pour que le joueur humain choisisse un joueur cible
        // Vous pouvez implémenter cette logique selon les règles spécifiques du jeu
        // Retournez le joueur choisi
        return null;
    }

    // Méthode pour défausser la première carte de la Pile du joueur
    private void defausserPileJoueur(Joueur joueur) {
        if (!joueur.getPile().getCartes().isEmpty()) {
            Carte carteADefausser = joueur.getPile().getCartes().remove(0);
            joueur.getDefausse().getCartes().add(carteADefausser);
        }
    }

    // Méthode pour défausser automatiquement une carte de la Pile (utilisée par l'ordinateur)
    private void defausserAutomatiquement(Joueur joueur) {
        // Logique pour que l'ordinateur choisisse et défausse automatiquement une carte de la Pile
        // Vous pouvez adapter cette logique en fonction des règles spécifiques du jeu
        if (!joueur.getPile().getCartes().isEmpty()) {
            Carte carteADefausser = joueur.getPile().getCartes().remove(0);
            joueur.getDefausse().getCartes().add(carteADefausser);
        }
    }

    // Méthode pour jouer automatiquement une autre carte (utilisée par l'ordinateur et le joueur humain)
    private void jouerAutreCarte(Joueur joueur) {
        if (joueur instanceof Ordinateur) {
            // Logique pour que l'ordinateur choisisse et joue automatiquement une autre carte
            jouerAutomatiquement(joueur);
        } else {
            // Logique pour que le joueur humain choisisse et joue une autre carte
            this.renderer.afficherCartes(joueur.getMain().getCartes());
            Carte carteAJouer = this.renderer.choisirUneCarte(joueur.getMain().getCartes());

            if (carteAJouer != null) {
                joueur.jouerCarte(carteAJouer);
            }
        }
    }

    // Méthode pour jouer automatiquement une autre carte (utilisée par l'ordinateur)
    private void jouerAutomatiquement(Joueur joueur) {
        // Logique pour que l'ordinateur choisisse et joue automatiquement une autre carte
        // Vous devez adapter cette logique en fonction des règles spécifiques du jeu
        List<Carte> cartesJouables = joueur.getMain().getCartesJouables();

        if (!cartesJouables.isEmpty()) {
            Carte carteAJouer = cartesJouables.get(0); // Choix aléatoire de la première carte jouable
            joueur.jouerCarte(carteAJouer);
        }
    }
}
