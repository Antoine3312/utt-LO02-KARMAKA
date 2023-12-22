/**
 * La classe {@code DernierSouffle} représente une carte du jeu avec le pouvoir spécifique de forcer
 * un adversaire à défausser une carte de sa main.
 * Elle hérite de la classe abstraite {@link Carte}.
 */
package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.Random;


public class DernierSouffle extends Carte {

    /**
     * Constructeur de la classe DernierSouffle.
     *
     * @param renderer L'objet permettant d'afficher des messages dans l'interface utilisateur.
     */
    public DernierSouffle(Renderable renderer) {
        super(renderer);
        this.nom = "Dernier Souffle";
        this.point = 1;
        this.couleur = NomCouleur.ROUGE;
        this.intitulePouvoir = "Votre rival défausse une carte de sa Main";
    }

    /**
     * Méthode pour jouer le pouvoir de la carte DernierSouffle.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Vérifie si le joueur receveur a des cartes en main
        if (!joueurReceveur.getMain().isEmpty()) {
            Carte carteChoisie = null;
            // Si le joueur receveur n'est pas un ordinateur, permet au joueur de choisir la carte à défausser
            if (!(joueurReceveur instanceof Ordinateur)) {
                carteChoisie = this.renderer.choisirUneCarte(joueurReceveur.getMain());
            } else {
                // Si le joueur receveur est un ordinateur, choisit aléatoirement une carte à défausser
                Random r = new Random();
                carteChoisie = joueurReceveur.getMain().get(r.nextInt(joueurReceveur.getMain().size()));
            }
            // Ajoute la carte choisie à la défausse et la retire de la main du joueur receveur
            EtatPartie.getInstance().getFosse().getCartes().add(carteChoisie);
            joueurReceveur.getMain().remove(carteChoisie);
        } else {
            this.renderer.displayErrorMessage("Impossible : Le joueur rival n'a aucune carte en main.");
        }
    }
}
