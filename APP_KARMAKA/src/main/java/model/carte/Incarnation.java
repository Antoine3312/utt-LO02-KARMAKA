package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.oeuvre.Oeuvre;

public class Incarnation<Oeuvre> extends Carte {

    // Constructeur de la carte "Incarnation"
    public Incarnation(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLANCHE; // Définition de la couleur de la carte
    }

    // Implémentation de la méthode jouerPouvoir() définie dans l'interface PouvoirCarte
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Choisissez une de vos Oeuvres
        Oeuvre oeuvreChoisie = choisirOeuvre(joueurAppelant);

        // Copiez son pouvoir
        if (oeuvreChoisie != null) {
            oeuvreChoisie.activerPouvoir(joueurAppelant, joueurReceveur);
        }
    }

    // Méthode pour choisir une Oeuvre (à adapter en fonction des règles spécifiques du jeu)
    private Oeuvre choisirOeuvre(Joueur joueurAppelant) {
        // Dans cette implémentation, on suppose que le choix de l'Oeuvre se fait manuellement (par le joueur ou l'ordinateur)
        return joueurAppelant.getOeuvres().stream()
                .findFirst()
                .orElse(null);
    }
}
