package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;

import java.util.List;

public abstract class Recyclage extends Carte {

    // Constructeur de la carte "Recyclage"
    public Recyclage(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
    }

    // Implémentation de la méthode jouerPouvoir() définie dans l'interface PouvoirCarte
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Ajoutez à votre Vie Future une des 3 dernières cartes de la Fosse
        List<Carte> cartesFosse = joueurReceveur.getFosse().getCartes();
        int tailleFosse = cartesFosse.size();

        if (tailleFosse >= 3) {
            // Choix des 3 dernières cartes de la Fosse
            List<Carte> troisDernieresCartes = cartesFosse.subList(tailleFosse - 3, tailleFosse);

            // Affichage des cartes pour permettre au joueur de choisir
            this.renderer.afficherCartes(troisDernieresCartes);

            // Choix de la carte à ajouter à la Vie Future (on suppose ici que le joueur choisit la première carte)
            Carte carteAAjouter = troisDernieresCartes.get(0);

            // Ajout de la carte à la Vie Future du joueur appelant
            joueurAppelant.getVieFutur().addCartes((List<Carte>) carteAAjouter);

            // Retrait de la carte de la Fosse
            joueurReceveur.getFosse().removeCartes((List<Carte>) carteAAjouter);
        }
    }
}
