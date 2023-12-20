package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;

/**
 * Classe abstraite représentant la carte "ReveBrises" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class ReveBrises extends Carte {

    /**
     * Constructeur de la carte "ReveBrises".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public ReveBrises(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "ReveBrises".
     * Transfère une carte de la Vie Futur du rival à la Vie Futur du joueur appelant.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur adverse dont la Vie Futur est affectée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        List<Carte> carteVieFuturRival = joueurReceveur.getVieFutur().getCartes();

        // Vérifier si le rival a des cartes dans sa Vie Futur
        if (!carteVieFuturRival.isEmpty()) {
            Carte carteRival = null;

            // Si le joueur appelant est un ordinateur, il choisit aléatoirement une carte Vie Futur du rival
            if (joueurAppelant instanceof Ordinateur) {
                carteRival = carteVieFuturRival.get(new Random().nextInt(carteVieFuturRival.size()));
            } else {
                // Si le joueur est humain, il choisit la première carte Vie Futur du rival
                carteRival = carteVieFuturRival.get(0);
            }

            // Transférer la carte Vie Futur du rival à la Vie Futur du joueur appelant
            joueurReceveur.getVieFutur().getCartes().remove(carteRival);
            joueurAppelant.getVieFutur().getCartes().add(carteRival);
        }
    }
}
