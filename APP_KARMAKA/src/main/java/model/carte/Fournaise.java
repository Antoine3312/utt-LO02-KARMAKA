package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;

import java.util.List;

public abstract class Fournaise extends Carte {

    // Constructeur de la carte "Fournaise"
    public Fournaise(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
    }

    // Implémentation de la méthode jouerPouvoir() définie dans l'interface PouvoirCarte
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Défaussez les 2 premières cartes de la Vie Future d'un rival
        List<Carte> cartesVieFuturRival = joueurReceveur.getVieFutur().getCartes();

        // Défausser les 2 premières cartes s'il y en a au moins 2
        if (cartesVieFuturRival.size() >= 2) {
            List<Carte> cartesADefausser = cartesVieFuturRival.subList(0, 2);

            // Défausser les cartes choisies
            joueurReceveur.getFosse().addCartes(cartesADefausser);
            joueurReceveur.getVieFutur().removeCartes(cartesADefausser);
        }
    }
}
