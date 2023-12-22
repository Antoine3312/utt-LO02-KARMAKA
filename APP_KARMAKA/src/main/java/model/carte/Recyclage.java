package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;

/**
 * La classe Recyclage représente une carte du jeu avec le pouvoir spécifique "Ajoutez à votre Vie Future une des 3 dernières cartes de la Fosse".
 * Elle hérite de la classe Carte et implémente l'interface Renderable.
 */
public class Recyclage extends Carte {

    /**
     * Constructeur de la classe Recyclage.
     *
     * @param renderable L'objet qui gère l'affichage de la carte.
     */
    public Recyclage(Renderable renderable) {
        super(renderable);
        this.nom = "Recyclage"; // Nom de la carte
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Ajoutez à votre Vie Future une des 3 dernières cartes de la Fosse"; // Description du pouvoir de la carte
    }

    /**
     * Méthode pour exécuter le pouvoir de la carte Recyclage.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s", joueurAppelant.getNom(), this.getNom()));
        List<Carte> fosse = EtatPartie.getInstance().getFosse().getCartes();
        if (fosse.size() >= 3) {
            Carte carteChoisie = null;
            if (!(joueurAppelant instanceof Ordinateur)) {
                // Laisser le joueur humain choisir une carte parmi les 3 dernières de la fosse
                carteChoisie = this.renderer.choisirUneCarte(fosse.subList(fosse.size() - 1, fosse.size() - 4));
            } else {
                // L'ordinateur choisit une carte au hasard parmi les 3 dernières de la fosse
                Random r = new Random();
                carteChoisie = fosse.get(r.nextInt(fosse.size()));
            }
            joueurAppelant.getVieFutur().getCartes().push(carteChoisie);
        } else {
            this.renderer.displayErrorMessage("Impossible : la source possède moins de 3 cartes.");
        }
    }
}
