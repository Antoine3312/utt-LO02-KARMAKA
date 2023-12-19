// Importation des classes nécessaires
package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

// Carte "Deni" qui hérite de la classe abstraite "Carte"
public abstract class Deni extends Carte {

    // Constructeur de la carte "Deni"
    public Deni(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
    }

    // Implémentation de la méthode jouerPouvoir() définie dans l'interface PouvoirCarte
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Si le joueur appelant est un ordinateur
        if (joueurAppelant instanceof Ordinateur) {
            // Appel de la méthode pour défausser automatiquement une carte
            defausserAutomatiquement(joueurAppelant);
        } else {
            // Si le joueur est humain
            this.renderer.afficherCartes(joueurAppelant.getMain().getCartes());

            // Le joueur humain choisit une carte à défausser
            Carte carteADefausser = this.renderer.choisirUneCarte(joueurAppelant.getMain().getCartes());

            // Si le joueur a effectivement choisi une carte
            if (carteADefausser != null) {
                // Retirer la carte de la main du joueur appelant
                joueurAppelant.getMain().getCartes().remove(carteADefausser);
                // Ajouter la carte à la défausse du joueur appelant
                joueurAppelant.getDefausse().getCartes().add(carteADefausser);

                // Copier le pouvoir de cette carte
                PouvoirCarte copieDeni = copierPouvoir(this);
                // Jouer le pouvoir de la carte copiée
                copieDeni.jouerPouvoir(joueurAppelant, joueurReceveur);
            }
        }
    }

    // Méthode pour défausser automatiquement une carte (utilisée par l'ordinateur)
    private void defausserAutomatiquement(Joueur joueur) {
        // Logique pour que l'ordinateur choisisse et défausse automatiquement une carte de sa main
        // Vous pouvez adapter cette logique en fonction des règles spécifiques du jeu
        if (!joueur.getMain().getCartes().isEmpty()) {
            // Choix aléatoire de la première carte de la main
            Carte carteADefausser = joueur.getMain().getCartes().get(0);
            // Retirer la carte de la main du joueur
            joueur.getMain().getCartes().remove(carteADefausser);
            // Ajouter la carte à la défausse du joueur
            joueur.getDefausse().getCartes().add(carteADefausser);

            // Copier le pouvoir de cette carte
            PouvoirCarte copieDeni = copierPouvoir(this);
            // Jouer le pouvoir de la carte copiée
            copieDeni.jouerPouvoir(joueur, joueur);
        }
    }

    // Méthode générique pour copier le pouvoir d'une carte
    private PouvoirCarte copierPouvoir(PouvoirCarte carte) {
        try {
            // Utilisation de la réflexion pour créer une nouvelle instance de la classe
            Class<?> clazz = carte.getClass();
            return (PouvoirCarte) clazz.getConstructor(Renderable.class).newInstance(carte.getRenderer());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

