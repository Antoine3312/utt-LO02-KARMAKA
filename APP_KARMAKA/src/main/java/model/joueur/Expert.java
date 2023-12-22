package model.joueur;

import application.control.ActionJouer;
import application.control.Renderable;
import model.carte.Carte;
import model.carte.NomCouleur;

import java.io.Serializable;
import java.util.*;

/**
 * La classe Expert représente la stratégie de jeu pour un joueur expert.
 * Elle implémente l'interface StyleJeuStrategy et Serializable.
 */
public class Expert implements StyleJeuStrategy, Serializable {
    private static final long serialVersionUID = 2711998155099132322L;

    // Couleur préférée pour les points
    private NomCouleur couleurPoint = null;

    // Couleur préférée pour la Vie Future
    private NomCouleur couleurFutur = null;

    // Couleur préférée pour les pouvoirs
    private NomCouleur couleurPouvoir = null;

    // Action pour jouer une carte
    public ActionJouer actionJouer;

    // Générateur de nombres aléatoires
    public Random r = new Random();

    // Objet pour gérer l'affichage
    private Renderable renderer;

    // Indique si l'initialisation a été effectuée
    private boolean hasInit = false;

    /**
     * Méthode pour jouer un tour selon la stratégie du joueur expert.
     *
     * @param joueur   Le joueur qui joue le tour.
     * @param renderer L'objet qui gère l'affichage.
     */
    @Override
    public void jouerTour(Joueur joueur, Renderable renderer) {
        this.renderer = renderer;
        this.actionJouer = new ActionJouer(this.renderer);

        // Initialiser les couleurs préférées si ce n'est pas déjà fait
        if (!this.hasInit) {
            this.choisirCouleurCartes(joueur.getMain());
            this.hasInit = true;
        }

        // Si le joueur n'a plus de carte ni dans la main ni dans la pile, il se réincarne et réinitialise ses couleurs préférées
        if (joueur.getMain().isEmpty() && joueur.getPile().getCartes().isEmpty()) {
            this.reincarner(joueur);
            this.choisirCouleurCartes(joueur.getMain());
        } else {
            this.jouer(joueur);
        }
    }

    /**
     * Méthode pour jouer une carte selon la stratégie du joueur expert.
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
            // Sélectionner une carte aléatoire dans la main du joueur
            Carte carteAJouer = joueur.getMain().get(this.r.nextInt(joueur.getMain().size()));

            // Jouer la carte en fonction de sa couleur préférée
            if (carteAJouer.getCouleur().equals(this.couleurPouvoir)) {
                this.actionJouer.jouer(joueur, carteAJouer, ActionJouer.UTILISATIONPOUVOIR);
            } else if (carteAJouer.getCouleur().equals(this.couleurPoint)) {
                this.actionJouer.jouer(joueur, carteAJouer, ActionJouer.UTILISATIONPOINT);
            } else if (carteAJouer.getCouleur().equals(this.couleurFutur)) {
                this.actionJouer.jouer(joueur, carteAJouer, ActionJouer.UTILISATIONFUTUR);
            } else if (carteAJouer.getCouleur().equals(NomCouleur.MOSAIQUE)) {
                this.actionJouer.jouer(joueur, carteAJouer, (r.nextInt(3)) + 1);
            } else {
                System.out.println("null");
            }
        } else {
            // Afficher un message si le joueur décide de passer son tour
            this.renderer.displayErrorMessage(String.format("%s décide de passer son tour. Attention, il prépare sûrement un plan diabolique ...", joueur.getNom()));
        }
    }

    /**
     * Méthode pour réincarner le joueur expert.
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
        if (utiliserAnneaux) {
            this.actionJouer.reincarner(joueur, couleurLaPlusRentable, true, this.r.nextInt(joueur.getNbAnneauxKarmique() + 1));
        } else {
            this.actionJouer.reincarner(joueur, couleurLaPlusRentable, false, 0);
        }
    }

    /**
     * Méthode pour choisir les couleurs préférées en fonction des cartes dans la main du joueur.
     *
     * @param main La main du joueur.
     */
    private void choisirCouleurCartes(List<Carte> main) {
        List<NomCouleur> couleurs = Arrays.asList(NomCouleur.BLEU, NomCouleur.ROUGE, NomCouleur.VERTE);
        Map<NomCouleur, Integer> couleurCarteMain = new HashMap<>();
        couleurs.forEach(c -> couleurCarteMain.put(c, 0));

        // Compter le nombre de cartes de chaque couleur dans la main du joueur
        for (Carte c : main) {
            if (c.getCouleur() != NomCouleur.MOSAIQUE) {
                couleurCarteMain.put(c.getCouleur(), couleurCarteMain.get(c.getCouleur()) + 1);
            }
        }

        // Trier les couleurs en fonction du nombre de cartes associées et attribuer les couleurs préférées
        List<Map.Entry<NomCouleur, Integer>> entryList = new ArrayList<>(couleurCarteMain.entrySet());
        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        this.couleurPoint = entryList.get(0).getKey();
        this.couleurFutur = entryList.get(1).getKey();
        this.couleurPouvoir = entryList.get(2).getKey();
    }
}
