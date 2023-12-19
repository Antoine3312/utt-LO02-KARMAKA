package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;

import java.util.List;

public abstract class Roulette extends Carte {

    // Constructeur de la carte "Roulette"
    public Roulette(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
    }

    // Implémentation de la méthode jouerPouvoir() définie dans l'interface PouvoirCarte
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Défaussez jusqu'à 2 cartes de votre Main
        List<Carte> cartesMain = joueurAppelant.getMain().getCartes();
        int nombreCartesADefausser = Math.min(2, cartesMain.size());

        // Affichage des cartes pour permettre au joueur de choisir
        this.renderer.afficherCartes(cartesMain);

        // Choix des cartes à défausser (on suppose ici que le joueur choisit les deux premières cartes)
        List<Carte> cartesADefausser = cartesMain.subList(0, nombreCartesADefausser);

        // Défausse des cartes choisies
        joueurAppelant.getFosse().addCartes(cartesADefausser);
        joueurAppelant.getMain().removeCartes(cartesADefausser);

        // Vous pouvez ensuite puiser à la Source autant de carte(s) + 1
        int nombreDeCartesAPuiser = joueurAppelant.puiserCartesSource(nombreCartesADefausser + 1);

        System.out.println("Vous avez puisé " + nombreDeCartesAPuiser + " carte(s) + 1 à la Source.");
    }
}
