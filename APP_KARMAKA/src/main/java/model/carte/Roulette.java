package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;

/**
 * La classe Roulette représente une carte du jeu avec le pouvoir spécifique "Défaussez 2 cartes de votre Main, puis puisez de la source 3 cartes".
 * Elle hérite de la classe Carte et implémente l'interface Renderable.
 */
public class Roulette extends Carte {

    /**
     * Constructeur de la classe Roulette.
     *
     * @param renderable L'objet qui gère l'affichage de la carte.
     */
    public Roulette(Renderable renderable) {
        super(renderable);
        this.nom = "Roulette"; // Nom de la carte
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Défaussez 2 cartes de votre Main, puis puisez de la source 3 cartes"; // Description du pouvoir de la carte
    }

    /**
     * Méthode pour exécuter le pouvoir de la carte Roulette.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée (non utilisé dans ce cas).
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        if (EtatPartie.getInstance().getSource().getCartes().size() >= 3 && joueurAppelant.getMain().size() >= 2) {
            if (!(joueurAppelant instanceof Ordinateur)) {
                // Laisser le joueur humain choisir deux cartes à défausser
                List<Carte> cartesChoisies = this.renderer.choisirDeuxCarte(joueurAppelant.getMain());
                EtatPartie.getInstance().getFosse().getCartes().addAll(cartesChoisies);
                joueurAppelant.getMain().removeAll(cartesChoisies);
            } else {
                // L'ordinateur choisit deux cartes au hasard à défausser
                Random r = new Random();
                for (int i = 0; i < 2; i++) {
                    Carte carteADefausser = joueurAppelant.getMain().get(r.nextInt(joueurAppelant.getMain().size()));
                    EtatPartie.getInstance().getFosse().getCartes().add(carteADefausser);
                    joueurAppelant.getMain().remove(carteADefausser);
                }
            }

            // Piocher 3 cartes de la source et les ajouter à la main du joueur
            for (int i = 0; i < 3; i++) {
                joueurAppelant.getMain().add(EtatPartie.getInstance().getSource().getCartes().pop());
            }
        } else {
            this.renderer.displayErrorMessage("Impossible : Le joueur appelant a moins de 2 cartes en main, ou il y a moins de 3 cartes dans la pioche.");
        }
    }
}
