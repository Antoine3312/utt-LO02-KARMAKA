package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;

/**
 * Classe abstraite représentant la carte "Panique" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class Panique extends Carte {

    /**
     * Constructeur de la carte "Panique".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Panique(Renderable renderable) {
        super(renderable);
        this.nom = "Panique";
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Panique".
     * Si le joueur appelant est un ordinateur, il défausse automatiquement une carte du rival et joue
     * automatiquement une autre carte. Si le joueur appelant est humain, il choisit un joueur cible, défausse
     * la première carte de la Pile du joueur cible, puis peut jouer une autre carte.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur adverse ciblé par le pouvoir.
     */
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

    /**
     * Méthode pour choisir un joueur cible.
     * Vous devez implémenter cette logique selon les règles spécifiques du jeu.
     *
     * @return Le joueur cible choisi.
     */
    private Joueur choisirJoueurCible() {
        // Logique pour que le joueur humain choisisse un joueur cible
        // Vous pouvez implémenter cette logique selon les règles spécifiques du jeu
        // Retournez le joueur choisi
        return null;
    }

    /**
     * Méthode pour défausser la première carte de la Pile du joueur.
     *
     * @param joueur Le joueur dont la Pile est affectée.
     */
    private void defausserPileJoueur(Joueur joueur) {
        if (!joueur.getPile().getCartes().isEmpty()) {
            Carte carteADefausser = joueur.getPile().getCartes().remove(0);
            joueur.getDefausse().getCartes().add(carteADefausser);
        }
    }

    /**
     * Méthode pour défausser automatiquement une carte de la Pile (utilisée par l'ordinateur).
     *
     * @param joueur Le joueur dont la Pile est affectée.
     */
    private void defausserAutomatiquement(Joueur joueur) {
        // Logique pour que l'ordinateur choisisse et défausse automatiquement une carte de la Pile
        if (!joueur.getPile().getCartes().isEmpty()) {
            Carte carteADefausser = joueur.getPile().getCartes().remove(0);
            joueur.getDefausse().getCartes().add(carteADefausser);
        }
    }

    /**
     * Méthode pour jouer automatiquement une autre carte (utilisée par l'ordinateur et le joueur humain).
     *
     * @param joueur Le joueur qui joue la carte.
     */
    private void jouerAutreCarte(Joueur joueur) {
        if (joueur instanceof Ordinateur) {
            // Logique pour que l'ordinateur choisisse et joue automatiquement une autre carte
            jouerAutomatiquement(joueur);
        } else {
            // Logique pour que le joueur humain choisisse et joue une autre carte
            this.renderer.afficherCartes(joueur.getMain());
            Carte carteAJouer = this.renderer.choisirUneCarte(joueur.getMain());

            if (carteAJouer != null) {
                joueur.jouerCarte(carteAJouer);
            }
        }
    }

    /**
     * Méthode pour jouer automatiquement une autre carte (utilisée par l'ordinateur).
     *
     * @param joueur Le joueur qui joue la carte.
     */
    private void jouerAutomatiquement(Joueur joueur) {
        // Logique pour que l'ordinateur choisisse et joue automatiquement une autre carte
        // Vous devez adapter cette logique en fonction des règles spécifiques du jeu
        List<Carte> cartesJouables = joueur.getMain();

        if (!cartesJouables.isEmpty()) {
            Carte carteAJouer = cartesJouables.get(0); // Choix aléatoire de la première carte jouable
            joueur.jouerCarte(carteAJouer);
        }
    }
}
