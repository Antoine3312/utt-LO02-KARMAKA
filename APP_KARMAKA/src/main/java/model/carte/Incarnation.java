package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;

/**
 * Classe abstraite représentant la carte "Incarnation" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public class Incarnation extends Carte {

    /**
     * Constructeur de la carte "Incarnation".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Incarnation(Renderable renderable) {
        super(renderable);
        this.nom = "Incarnation";
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.MOSAIQUE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Incarnation".
     * Choisissez une de vos œuvres. Copiez son pouvoir.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur cible du pouvoir.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s.", joueurAppelant.getNom(), this.getNom()));
        if(!joueurAppelant.getOeuvre().getCartes().isEmpty()){
            Carte carteChoisie = null;
            if (joueurAppelant instanceof Ordinateur){
                Random r = new Random();
                List<Carte> cartesAChosisir = joueurAppelant.getOeuvre().getCartes().stream().filter(carte -> carte.getNom()!=this.getNom()).toList();
                if (cartesAChosisir.isEmpty()) {
                    this.renderer.displayErrorMessage("Impossible : Impossible de copier le pouvoir de la carte qui viens d'être jouée.");
                } else {
                    carteChoisie = cartesAChosisir.get(r.nextInt(cartesAChosisir.size()));
                }
            } else {
                carteChoisie = this.renderer.choisirUneCarte(joueurAppelant.getOeuvre().getCartes());
            }
            if (carteChoisie != null){
                carteChoisie.jouerPouvoir(joueurAppelant, joueurReceveur);
            }
        } else {
            this.renderer.displayErrorMessage("Impossible : Vous n'avez aucune oeuvre.");
        }
    }
}
