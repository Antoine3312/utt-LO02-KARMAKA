package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;


public abstract class Mimetisme extends Carte {

    // Constructeur de la carte "Mimétisme"
    public Mimetisme(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.MOSAIQUE; // Définition de la couleur de la carte
    }

    // Implémentation de la méthode jouerPouvoir() définie dans l'interface PouvoirCarte
    public <Oeuvre> void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Choisissez un Rival
        // Dans cette implémentation, on suppose que le choix du rival se fait manuellement (par le joueur ou l'ordinateur)
        Joueur rivalChoisi = choisirRival(joueurAppelant);

        // Copiez le pouvoir de son Oeuvre Exposée
        if (rivalChoisi != null) {
            Oeuvre oeuvreExposeeRival = (Oeuvre) rivalChoisi.getOeuvreExposee();

            if (oeuvreExposeeRival != null) {
                // Copie du pouvoir de l'Oeuvre Exposée du rival
                oeuvreExposeeRival.activerPouvoir(joueurAppelant, joueurReceveur);
            }
        }
    }

    // Méthode pour choisir un rival (à adapter en fonction des règles spécifiques du jeu)
    private Joueur choisirRival(Joueur joueurAppelant) {
        // Dans cette implémentation, on suppose que le choix du rival se fait aléatoirement pour l'ordinateur
        if (joueurAppelant instanceof Ordinateur) {
            return joueurAppelant.getPartie().getJoueurs().stream()
                    .filter(joueur -> !joueur.equals(joueurAppelant)) // Exclure le joueur appelant
                    .findFirst()
                    .orElse(null);
        } else {
            // Dans le cas d'un joueur humain, l'interaction pour choisir le rival doit être implémentée
            // (par exemple, via une interface utilisateur)
            return null; // À remplacer par la logique de choix du joueur humain
        }
    }
}
