package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.Random;

/**
 * La classe Transmigration représente une carte du jeu avec le pouvoir spécifique "Placez dans votre Main n'importe quelle carte de votre Vie Future".
 * Elle hérite de la classe Carte et implémente l'interface Renderable.
 */
public class Transmigration extends Carte {

    /**
     * Constructeur de la classe Transmigration.
     *
     * @param renderable L'objet qui gère l'affichage de la carte.
     */
    public Transmigration(Renderable renderable) {
        super(renderable);
        this.nom = "Transmigration"; // Nom de la carte
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
        this.intitulePouvoir = "Placez dans votre Main n'importe quelle carte de votre Vie Future"; // Description du pouvoir de la carte
    }

    /**
     * Méthode pour exécuter le pouvoir de la carte Transmigration.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée (non utilisé dans ce cas).
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s", joueurAppelant.getNom(), this.getNom()));
        if (!joueurAppelant.getVieFutur().getCartes().isEmpty()) {
            Carte carteChoisi = null;
            if (!(joueurAppelant instanceof Ordinateur)) {
                // Laisser le joueur humain choisir une carte de sa Vie Future
                carteChoisi = this.renderer.choisirUneCarte(joueurAppelant.getVieFutur().getCartes());
            } else {
                // L'ordinateur choisit une carte au hasard de sa Vie Future
                Random r = new Random();
                carteChoisi = joueurAppelant.getVieFutur().getCartes().get(r.nextInt(joueurAppelant.getVieFutur().getCartes().size()));
            }
            joueurAppelant.getMain().add(carteChoisi);
            joueurAppelant.getVieFutur().getCartes().remove(carteChoisi);
        } else {
            this.renderer.displayErrorMessage("Impossible : Le joueur appelant n'a aucune carte dans sa vie future.");
        }
    }
}
