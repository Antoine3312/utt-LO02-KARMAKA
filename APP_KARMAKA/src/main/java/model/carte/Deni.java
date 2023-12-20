package model.carte;

import application.control.Renderable;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.joueur.Joueur;

import java.util.List;
import java.util.Scanner;

/**
 * Carte "Deni" qui hérite de la classe abstraite "Carte".
 * Représente une carte permettant au joueur de défausser une carte de sa main et de copier le pouvoir de la carte défaussée.
 */
public abstract class Deni extends Carte {

    /**
     * Constructeur de la carte "Deni".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Deni(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Deni".
     * Le joueur appelant défausse une carte de sa main et copie le pouvoir de la carte défaussée.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur cible du pouvoir (non utilisé dans ce contexte).
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Le joueur appelant défausse une carte de sa main
        defausserCarte(joueurAppelant);

        // Copiez le pouvoir de la carte défaussée
        copierPouvoirCarte(joueurAppelant);
    }

    /**
     * Méthode privée pour défausser une carte de la main du joueur.
     *
     * @param joueur Le joueur qui défausse la carte.
     */
    private void defausserCarte(Joueur joueur) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choisissez une carte à défausser (entrez le numéro correspondant) :");

        // Afficher la liste des cartes en main du joueur
        this.renderer.afficherCartes(joueur.getMain());

        int choixCarte = scanner.nextInt();

        // Défausser la carte choisie par le joueur
        joueur.defausserCarte(choixCarte);
    }

    /**
     * Méthode privée pour copier le pouvoir de la carte défaussée.
     *
     * @param joueur Le joueur qui copie le pouvoir.
     */
    private void copierPouvoirCarte(Joueur joueur) {
        // Logique pour copier le pouvoir de la carte défaussée

        List<Carte> cartesDefaussees = joueur.getPartie().getDefausse().getCartes();
        if (!cartesDefaussees.isEmpty()) {
            Carte carteDefaussee = cartesDefaussees.get(cartesDefaussees.size() - 1); // Dernière carte défaussée
            getPouvoir pouvoirCopie = carteDefaussee.getPouvoir();

            // Appliquer le pouvoir copié
            if (pouvoirCopie != null) {
                pouvoirCopie.appliquerPouvoir(joueur, joueur);
            }
        }
    }
}


