package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;

import java.util.List;

public abstract class Longevite extends Carte {

    // Constructeur de la carte "Longevite"
    public Longevite(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
    }

    // Implémentation de la méthode jouerPouvoir() définie dans l'interface PouvoirCarte
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Placez 2 cartes puisées à la Source sur la Pile du joueur adverse
        int nombreCartesAPuiser = 2;

        // Puisez 2 cartes à la Source
        int cartesPuisées = joueurAppelant.puiserCartesSource(nombreCartesAPuiser);

        // Placez les cartes sur la Pile du joueur adverse (joueurReceveur)
        joueurReceveur.getPile().addCartes(cartesPuisees);
    }
}
