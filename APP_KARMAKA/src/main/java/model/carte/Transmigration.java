package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;

/**
 * Classe abstraite représentant la carte "Transmigration" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public class Transmigration extends Carte {

    /**
     * Constructeur de la carte "Transmigration".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Transmigration(Renderable renderable) {
        super(renderable);
        this.nom = "Transmigration";
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Transmigration".
     * Le joueur choisit une carte Vie Futur à transférer de sa Vie Futur à sa Main.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur adverse (non utilisé dans ce contexte).
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s", joueurAppelant.getNom(), this.getNom()));
        if(!joueurAppelant.getVieFutur().getCartes().isEmpty()) {
            Carte carteChoisi = null;
            if(!(joueurAppelant instanceof  Ordinateur)){
                carteChoisi = this.renderer.choisirUneCarte(joueurAppelant.getVieFutur().getCartes());
            } else {
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
