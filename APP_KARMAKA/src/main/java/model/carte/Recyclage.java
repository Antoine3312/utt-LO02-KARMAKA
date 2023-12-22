package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;

import java.util.List;

/**
 * Classe abstraite représentant la carte "Recyclage" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class Recyclage extends Carte {

    /**
     * Constructeur de la carte "Recyclage".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Recyclage(Renderable renderable) {
        super(renderable);
        this.nom = "Recyclage";
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Recyclage".
     * Ajoutez à votre Vie Future une des 3 dernières cartes de la Fosse du joueur adverse.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur adverse dont la Fosse est affectée.
     */
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
