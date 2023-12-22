package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * La classe Semis représente une carte du jeu avec le pouvoir spécifique "Placez 2 cartes à la Source, puis placez sur votre Vie Future 2 cartes de votre Main".
 * Elle hérite de la classe Carte et implémente l'interface Renderable.
 */
public class Semis extends Carte {

    /**
     * Constructeur de la classe Semis.
     *
     * @param renderable L'objet qui gère l'affichage de la carte.
     */
    public Semis(Renderable renderable) {
        super(renderable);
        this.nom = "Semis"; // Nom de la carte
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Placez 2 cartes à la Source, puis placez sur votre Vie Future 2 cartes de votre Main"; // Description du pouvoir de la carte
    }

    /**
     * Méthode pour exécuter le pouvoir de la carte Semis.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée (non utilisé dans ce cas).
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s", joueurAppelant.getNom(), this.getNom()));
        if (EtatPartie.getInstance().getSource().getCartes().size() >= 2) {
            for (int i = 0; i < 2; i++) {
                joueurAppelant.getMain().add(EtatPartie.getInstance().getSource().getCartes().pop());
            }
            List<Carte> cartesChoisies = new ArrayList<>();
            if (!(joueurAppelant instanceof Ordinateur)) {
                // Laisser le joueur humain choisir deux cartes de sa main
                cartesChoisies = this.renderer.choisirDeuxCarte(joueurAppelant.getMain());
                joueurAppelant.getMain().removeAll(cartesChoisies);
            } else {
                // L'ordinateur choisit deux cartes au hasard de sa main
                Random r = new Random();
                for (int i = 0; i < 2; i++) {
                    cartesChoisies.add(joueurAppelant.getMain().get(r.nextInt(joueurAppelant.getMain().size())));
                    joueurAppelant.getMain().removeAll(cartesChoisies);
                }
            }
            joueurAppelant.getVieFutur().getCartes().addAll(cartesChoisies);
        } else {
            this.renderer.displayErrorMessage("Impossible : Il y a moins de 2 cartes dans la source.");
        }
    }
}


