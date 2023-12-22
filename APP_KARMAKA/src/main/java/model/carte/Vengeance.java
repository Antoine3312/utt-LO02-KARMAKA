package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

/**
 * Classe abstraite représentant la carte "Vengeance" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class Vengeance extends Carte {

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
        // Si le joueur appelant est un ordinateur, il défausse automatiquement l'œuvre exposée de l'autre joueur
        if (joueurAppelant instanceof Ordinateur) {
            defausserOeuvreExposeeAutomatiquement(joueurReceveur);
        } else {
            // Si le joueur est humain, il défausse l'œuvre exposée de l'autre joueur en utilisant l'interface graphique
            defausserOeuvreExposee(joueurReceveur);
        }
    }

    /**
     * Méthode privée pour défausser l'œuvre exposée d'un joueur (utilisée par le joueur humain).
     *
     * @param joueur Le joueur dont l'œuvre exposée doit être défaussée.
     */
    private void defausserOeuvreExposee(Joueur joueur) {
        // Défaussez l’Oeuvre Exposée du joueur
        if (!joueur.getOeuvres().getExposition().getCartes().isEmpty()) {
            Carte carteADefausser = joueur.getOeuvres().getExposition().getCartes().remove(0);
            joueur.getDefausse().getCartes().add(carteADefausser);
        }
    }

    /**
     * Méthode privée pour que l'ordinateur choisisse et défausse automatiquement l’Oeuvre Exposée.
     *
     * @param joueur Le joueur dont l'œuvre exposée doit être défaussée.
     */
    private void defausserOeuvreExposeeAutomatiquement(Joueur joueur) {
        // Logique pour que l'ordinateur choisisse et défausse automatiquement l’Oeuvre Exposée
        // Vous pouvez adapter cette logique en fonction des règles spécifiques du jeu
        if (!joueur.getOeuvres().getExposition().getCartes().isEmpty()) {
            Carte carteADefausser = joueur.getOeuvres().getExposition().getCartes().remove(0);
            joueur.getDefausse().getCartes().add(carteADefausser);
        }
    }
}
