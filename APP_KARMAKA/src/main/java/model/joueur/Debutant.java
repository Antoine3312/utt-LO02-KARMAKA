package model.joueur;

import application.control.ActionJouer;
import application.control.Renderable;
import model.carte.Carte;
import model.carte.NomCouleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * La classe Debutant représente la stratégie de jeu pour un joueur débutant.
 * Elle implémente l'interface StyleJeuStrategy et Serializable.
 */
public class Debutant implements StyleJeuStrategy, Serializable {

    private static final long serialVersionUID = 2711998155099132322L;

    // Action pour jouer une carte
    public ActionJouer actionJouer;

    // Générateur de nombres aléatoires
    public Random r = new Random();

    // Objet pour gérer l'affichage
    private Renderable renderer;

    /**
     * Méthode pour jouer un tour selon la stratégie du joueur débutant.
     *
     * @param joueur    Le joueur qui joue le tour.
     * @param renderer  L'objet qui gère l'affichage.
     */
    @Override
    public void jouerTour(Joueur joueur, Renderable renderer) {
        this.renderer = renderer;
        this.actionJouer = new ActionJouer(this.renderer);

        // Si le joueur n'a plus de carte ni dans la main ni dans la pile, il se réincarne
        if (joueur.getMain().isEmpty() && joueur.getPile().getCartes().isEmpty()) {
            this.reincarner(joueur);
        } else {
            this.jouer(joueur);
        }
    }

    /**
     * Méthode pour réincarner le joueur débutant.
     *
     * @param joueur Le joueur à réincarner.
     */
    private void reincarner(Joueur joueur) {
        this.renderer.displayMessage(String.format("%s n'a plus aucune carte dans sa main et dans sa pile, il va se réincarner ...", joueur.getNom()));

        // Déterminer la couleur la plus rentable en fonction des couleurs de l'oeuvre
        NomCouleur couleurLaPlusRentable = null;
        if (!joueur.getOeuvre().getCartes().isEmpty()) {
            List<String> couleurs = new ArrayList<>(joueur.getOeuvre().getCouleursInStack());
            couleurLaPlusRentable = NomCouleur.valueOf(couleurs.get(r.nextInt(couleurs.size())));
        }

        // Utiliser les anneaux karmiques avec une probabilité de 50%
        boolean utiliserAnneaux = this.r.nextInt(2) == 1;
        if (joueur.getNbAnneauxKarmique() > 0 && utiliserAnneaux) {
            this.actionJouer.reincarner(joueur, couleurLaPlusRentable, true, this.r.nextInt(joueur.getNbAnneauxKarmique() + 1));
        } else {
            this.actionJouer.reincarner(joueur, couleurLaPlusRentable, false, 0);
        }
    }

    /**
     * Méthode pour jouer une carte.
     *
     * @param joueur Le joueur qui joue la carte.
     */
    private void jouer(Joueur joueur) {
        // Ajouter une carte de la pile à la main du joueur
        if (!joueur.getPile().getCartes().isEmpty()) {
            joueur.getMain().add(joueur.getPile().getCartes().pop());
        }

        // Décider de jouer une carte avec une probabilité de 75%
        boolean jouerCarte = !(this.r.nextInt(4) == 1);
        if (jouerCarte) {
            // Sélectionner une carte aléatoire dans la main du joueur et jouer avec une force aléatoire entre 1 et 3
            Carte carteAJouer = joueur.getMain().get(this.r.nextInt(joueur.getMain().size()));
            this.actionJouer.jouer(joueur, carteAJouer, (r.nextInt(3)) + 1);
        }
    }
}

