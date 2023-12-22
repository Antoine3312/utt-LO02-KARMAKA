package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;

/**
 * Classe abstraite représentant la carte "Recyclage" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public class Recyclage extends Carte {

    /**
     * Constructeur de la carte "Recyclage".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Recyclage(Renderable renderable) {
        super(renderable);
        this.nom = "Recyclage";
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Ajoutez à votre Vie Future une des 3 dernières cartes de la Fosse";
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Recyclage".
     * Ajoutez à votre Vie Future une des 3 dernières cartes de la Fosse du joueur adverse.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur adverse dont la Fosse est affectée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s", joueurAppelant.getNom(), this.getNom()));
        List<Carte> fosse = EtatPartie.getInstance().getFosse().getCartes();
        if(fosse.size() >= 3) {
            Carte carteChoisie = null;
            if(!(joueurAppelant instanceof Ordinateur)){
                carteChoisie = this.renderer.choisirUneCarte(fosse.subList(fosse.size()-1, fosse.size()-4));
            } else {
                Random r = new Random();
                carteChoisie = fosse.get(r.nextInt(fosse.size()));
            }
            joueurAppelant.getVieFutur().getCartes().push(carteChoisie);
        } else {
            this.renderer.displayErrorMessage("Impossible : la source possède moins de 3 cartes.");
        }
    }
}
