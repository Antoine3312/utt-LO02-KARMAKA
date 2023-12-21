package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Scanner;

/**
 * Carte "DernierSouffle" qui hérite de la classe abstraite "Carte".
 * Représente une carte permettant au joueur appelant de choisir un joueur cible qui devra défausser une carte de sa main.
 */
public abstract class DernierSouffle extends Carte {

    /**
     * Constructeur de la carte "DernierSouffle".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public DernierSouffle(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "DernierSouffle".
     * Le joueur appelant choisit un joueur cible qui doit défausser une carte de sa main.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur cible du pouvoir (non utilisé dans ce contexte).
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Le joueur appelant choisit un joueur cible
        Joueur joueurCible = choisirJoueurCible(joueurAppelant);

        if (joueurCible != null) {
            // Le joueur cible défausse une carte de sa main
            if (joueurCible instanceof Ordinateur) {
                defausserCarteAutomatiquement((Ordinateur) joueurCible);
            } else {
                defausserCarteHumain(joueurCible);
            }
        }
    }

    /**
     * Méthode privée pour choisir un joueur cible parmi les autres joueurs de la partie.
     *
     * @param joueurAppelant Le joueur qui fait le choix.
     * @return Le joueur cible choisi ou null si l'entrée n'est pas valide.
     */
    private Joueur choisirJoueurCible(Joueur joueurAppelant) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choisissez un joueur cible (entrez le numéro correspondant) :");

        // Afficher la liste des joueurs possibles (excluant le joueur appelant)
        List<Joueur> joueursPossibles = joueurAppelant.getPartie().getJoueurs();
        joueursPossibles.remove(joueurAppelant);

        for (int i = 0; i < joueursPossibles.size(); i++) {
            System.out.println((i + 1) + ". " + joueursPossibles.get(i).getNom());
        }

        int choix = scanner.nextInt();

        // Retourner le joueur cible choisi ou null si l'entrée n'est pas valide
        return (choix > 0 && choix <= joueursPossibles.size()) ? joueursPossibles.get(choix - 1) : null;
    }

    /**
     * Méthode privée pour demander au joueur cible humain de défausser une carte de sa main.
     *
     * @param joueurCible Le joueur cible qui doit défausser une carte.
     */
    private void defausserCarteHumain(Joueur joueurCible) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choisissez une carte à défausser (entrez le numéro correspondant) :");

        // Afficher la liste des cartes en main du joueur cible
        this.renderer.afficherCartes(joueurCible.getMain());

        int choixCarte = scanner.nextInt();

        // Défausser la carte choisie par le joueur cible
        joueurCible.defausserCarte(choixCarte);
    }

    /**
     * Méthode privée pour que l'ordinateur défausse automatiquement une carte de sa main.
     *
     * @param ordinateur L'ordinateur qui doit défausser une carte.
     */
    private void defausserCarteAutomatiquement(Ordinateur ordinateur) {
        // Logique pour que l'ordinateur choisisse et défausse automatiquement une carte
        List<Carte> cartesMainOrdinateur = ordinateur.getMain();

        if (!cartesMainOrdinateur.isEmpty()) {
            Carte carteADefausser = cartesMainOrdinateur.get(0); // Choix automatique de la première carte
            ordinateur.defausserCarte(carteADefausser.getPoint());
        }
    }
}
