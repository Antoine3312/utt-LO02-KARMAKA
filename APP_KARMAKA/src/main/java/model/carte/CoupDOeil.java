/**
 * La classe {@code CoupDOeil} représente une carte du jeu avec le pouvoir spécifique de regarder
 * la main d'un adversaire.
 * Elle hérite de la classe abstraite {@link Carte}.

 */
package model.carte;

import application.control.Renderable;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.Collections;
import java.util.List;


public class CoupDOeil extends Carte {

    /**
     * Constructeur de la classe CoupDOeil.
     *
     * @param renderable L'objet permettant d'afficher des messages dans l'interface utilisateur.
     */
    public CoupDOeil(Renderable renderable) {
        super(renderable);
        this.nom = "Coup d'Oeil";
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
        this.intitulePouvoir = "Regardez la Main d'un rival";
    }

    /**
     * Méthode pour jouer le pouvoir de la carte CoupDOeil.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        // Vérifie si le joueur appelant n'est pas un ordinateur avant d'afficher la main du joueur receveur
        if (!(joueurAppelant instanceof Ordinateur)) {
            this.renderer.afficherCartes(joueurReceveur.getMain());
        }
    }
}

