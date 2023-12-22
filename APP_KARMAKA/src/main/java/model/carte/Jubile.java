package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;

/**
 * Classe abstraite représentant la carte "Jubilé" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class Jubile extends Carte {

    /**
     * Constructeur de la carte "Jubilé".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Jubile(Renderable renderable) {
        super(renderable);
        this.nom = "Jubile";
        this.point = 3; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Jubilé".
     * Le joueur humain choisit jusqu'à 2 cartes de sa main à placer sur ses œuvres.
     * L'ordinateur choisit automatiquement jusqu'à 2 cartes à placer.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur cible du pouvoir.
     */
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

    /**
     * Méthode pour choisir automatiquement jusqu'à 2 cartes à placer (utilisée par l'ordinateur).
     *
     * @param cartesDansLaMain La liste des cartes dans la main du joueur.
     * @param joueurAppelant   Le joueur qui joue la carte.
     */
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
