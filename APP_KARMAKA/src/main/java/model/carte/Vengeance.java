package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

// Carte "Vengeance" qui hérite de la classe abstraite "Carte"
public abstract class Vengeance extends Carte {

    // Constructeur de la carte "Vengeance"
    public Vengeance(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
    }

    // Implémentation de la méthode jouerPouvoir() définie dans l'interface PouvoirCarte
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

    // Méthode privée pour défausser l'œuvre exposée d'un joueur (utilisée par le joueur humain)
    private void defausserOeuvreExposee(Joueur joueur) {
        // Défaussez l’Oeuvre Exposée du joueur
        if (!joueur.getOeuvres().getExposition().getCartes().isEmpty()) {
            Carte carteADefausser = joueur.getOeuvres().getExposition().getCartes().remove(0);
            joueur.getDefausse().getCartes().add(carteADefausser);
        }
    }

    // Méthode privée pour que l'ordinateur choisisse et défausse automatiquement l’Oeuvre Exposée
    private void defausserOeuvreExposeeAutomatiquement(Joueur joueur) {
        // Logique pour que l'ordinateur choisisse et défausse automatiquement l’Oeuvre Exposée
        // Vous pouvez adapter cette logique en fonction des règles spécifiques du jeu
        if (!joueur.getOeuvres().getExposition().getCartes().isEmpty()) {
            Carte carteADefausser = joueur.getOeuvres().getExposition().getCartes().remove(0);
            joueur.getDefausse().getCartes().add(carteADefausser);
        }
    }
}