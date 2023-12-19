package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;

import java.util.List;

public abstract class Duperie extends Carte {

    // Constructeur de la carte "Duperie"
    public Duperie(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
    }

    // Implémentation de la méthode jouerPouvoir() définie dans l'interface PouvoirCarte
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Regardez 3 cartes de la Main d'un rival
        Joueur rivalChoisi = choisirRival(joueurAppelant);

        if (rivalChoisi != null) {
            List<Carte> cartesMainRival = rivalChoisi.getMain().getCartes();

            // Affichez les 3 premières cartes de la Main du rival (à adapter en fonction des règles spécifiques du jeu)
            List<Carte> cartesVisible = cartesMainRival.subList(0, Math.min(cartesMainRival.size(), 3));
            this.renderer.afficherCartes(cartesVisible);

            // Choisissez une carte à ajouter à votre Main
            Carte carteChoisie = choisirCarteDuperie(cartesVisible);

            // Ajoutez la carte choisie à votre Main
            if (carteChoisie != null) {
                joueurAppelant.getMain().addCartes(carteChoisie);
            }
        }
    }

    // Méthode pour choisir un rival (à adapter en fonction des règles spécifiques du jeu)
    private Joueur choisirRival(Joueur joueurAppelant) {
        // Dans cette implémentation, on suppose que le choix du rival se fait aléatoirement pour l'ordinateur
        if (joueurAppelant instanceof Ordinateur) {
            List<Joueur> joueursPossibles = joueurAppelant.getPartie().getJoueurs();
            joueursPossibles.remove(joueurAppelant); // Exclure le joueur appelant
            return joueursPossibles.isEmpty() ? null : joueursPossibles.get(0); // Choisir le premier joueur possible
        } else {
            // Dans le cas d'un joueur humain, l'interaction pour choisir le rival doit être implémentée
            // (par exemple, via une interface utilisateur)
            return null; // À remplacer par la logique de choix du joueur humain
        }
    }

    // Méthode pour choisir une carte parmi celles affichées pendant la Duperie
    private Carte choisirCarteDuperie(List<Carte> cartesVisible) {
        // Dans cette implémentation, on suppose que le choix de la carte se fait manuellement (par le joueur ou l'ordinateur)
        if (!cartesVisible.isEmpty()) {
            // On choisit arbitrairement la première carte visible
            return cartesVisible.get(0);
        } else {
            return null;
        }
    }
}
