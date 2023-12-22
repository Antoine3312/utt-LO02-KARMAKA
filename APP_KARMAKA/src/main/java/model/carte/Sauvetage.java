package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;

import java.util.List;

/**
 * Classe abstraite représentant la carte "Sauvetage" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class Sauvetage extends Carte {

    /**
     * Constructeur de la carte "Sauvetage".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Sauvetage(Renderable renderable) {
        super(renderable);
        this.nom = "Sauvetage";
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Sauvetage".
     * Ajoute à votre Main une des 3 dernières cartes de la Fosse du joueur adverse.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur adverse.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Ajoutez à votre Main une des 3 dernières cartes de la Fosse
        List<Carte> cartesFosse = joueurReceveur.getFosse().getCartes();
        int tailleFosse = cartesFosse.size();

        if (tailleFosse >= 3) {
            // Choix des 3 dernières cartes de la Fosse
            List<Carte> troisDernieresCartes = cartesFosse.subList(tailleFosse - 3, tailleFosse);

            // Affichage des cartes pour permettre au joueur de choisir
            this.renderer.afficherCartes(troisDernieresCartes);

            // Choix de la carte à ajouter à la Main (on suppose ici que le joueur choisit la première carte)
            Carte carteAAjouter = troisDernieresCartes.get(0);

            // Ajout de la carte à la Main du joueur appelant
            joueurAppelant.getMain().add(carteAAjouter);

            // Retrait de la carte de la Fosse
            joueurReceveur.getFosse().removeCartes((List<Carte>) carteAAjouter);
        }
    }
}
