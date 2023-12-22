package model.joueur;

import application.control.Renderable;

/**
 * L'interface StyleJeuStrategy définit un contrat pour les différentes stratégies de jeu des joueurs.
 */
public interface StyleJeuStrategy {

    /**
     * Méthode pour définir le comportement de jeu d'un joueur pendant un tour.
     *
     * @param joueur   Le joueur pour lequel la stratégie est appliquée.
     * @param renderer L'objet Renderable utilisé pour afficher des messages ou des erreurs.
     */
    void jouerTour(Joueur joueur, Renderable renderer);
}
