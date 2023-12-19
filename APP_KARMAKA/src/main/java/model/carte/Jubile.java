package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;

// Carte "Jubilé" qui hérite de la classe abstraite "Carte"
public abstract class Jubile extends Carte {

    // Constructeur de la carte "Jubilé"
    public Jubile(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
    }

    // Implémentation de la méthode jouerPouvoir() définie dans l'interface PouvoirCarte
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        List<Carte> cartesDansLaMain = joueurAppelant.getMain();

        // Le joueur humain choisit les cartes à placer
        if (joueurAppelant instanceof Ordinateur) {
            jouerAutomatiquement(cartesDansLaMain, joueurAppelant);
        } else {
            // Le joueur humain choisit jusqu'à 2 cartes de sa main à placer sur ses œuvres
            for (int i = 0; i < 2 && !cartesDansLaMain.isEmpty(); i++) {
                this.renderer.afficherCartes(cartesDansLaMain);
                Carte carteAPlacer = this.renderer.choisirUneCarte(cartesDansLaMain);

                if (carteAPlacer != null) {
                    // Retirer la carte de la main
                    cartesDansLaMain.remove(carteAPlacer);
                    // Ajouter la carte aux œuvres du joueur
                    joueurAppelant.getOeuvres().getCartes().add(carteAPlacer);
                }
            }
        }
    }

    // Méthode pour choisir automatiquement jusqu'à 2 cartes à placer (utilisée par l'ordinateur)
    private void jouerAutomatiquement(List<Carte> cartesDansLaMain, Joueur joueurAppelant) {
        // Logique pour que l'ordinateur choisisse automatiquement jusqu'à 2 cartes à placer
        for (int i = 0; i < 2 && !cartesDansLaMain.isEmpty(); i++) {
            // Choix aléatoire d'une carte à placer
            Carte carteAPlacer = cartesDansLaMain.get(new Random().nextInt(cartesDansLaMain.size()));

            // Retirer la carte de la main
            cartesDansLaMain.remove(carteAPlacer);
            // Ajouter la carte aux œuvres du joueur
            joueurAppelant.getOeuvres().getCartes().add(carteAPlacer);
        }
    }
}
