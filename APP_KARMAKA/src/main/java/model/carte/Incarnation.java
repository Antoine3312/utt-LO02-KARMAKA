/**
 * La classe {@code Incarnation} représente une carte du jeu avec le pouvoir spécifique de choisir
 * une carte parmi ses propres œuvres et d'utiliser son pouvoir.
 * Elle hérite de la classe abstraite {@link Carte}.
 */
package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;


public class Incarnation extends Carte {

    /**
     * Constructeur de la classe Incarnation.
     *
     * @param renderable L'objet permettant d'afficher des messages dans l'interface utilisateur.
     */
    public Incarnation(Renderable renderable) {
        super(renderable);
        this.nom = "Incarnation";
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.MOSAIQUE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Choisissez une de vos œuvres et utiliser son pouvoir";
    }

    /**
     * Méthode pour jouer le pouvoir de la carte Incarnation.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s.", joueurAppelant.getNom(), this.getNom()));
        // Vérifie si le joueur appelant a des œuvres
        if (!joueurAppelant.getOeuvre().getCartes().isEmpty()) {
            Carte carteChoisie = null;
            // Si le joueur appelant est un ordinateur, choisit aléatoirement une carte parmi ses œuvres
            if (joueurAppelant instanceof Ordinateur) {
                Random r = new Random();
                List<Carte> cartesAChosisir = joueurAppelant.getOeuvre().getCartes().stream()
                        .filter(carte -> carte.getNom() != this.getNom())
                        .toList();
                // Vérifie si le joueur peut choisir une carte (évite de choisir la carte qui vient d'être jouée)
                if (cartesAChosisir.isEmpty()) {
                    this.renderer.displayErrorMessage("Impossible : Impossible de copier le pouvoir de la carte qui vient d'être jouée.");
                } else {
                    carteChoisie = cartesAChosisir.get(r.nextInt(cartesAChosisir.size()));
                }
            } else {
                // Si le joueur appelant est un joueur humain, lui permet de choisir une carte parmi ses œuvres
                carteChoisie = this.renderer.choisirUneCarte(joueurAppelant.getOeuvre().getCartes());
            }

            // Vérifie si une carte a été choisie
            if (carteChoisie != null) {
                // Utilise le pouvoir de la carte choisie
                carteChoisie.jouerPouvoir(joueurAppelant, joueurReceveur);
            }
        } else {
            this.renderer.displayErrorMessage("Impossible : Vous n'avez aucune œuvre.");
        }
    }
}
