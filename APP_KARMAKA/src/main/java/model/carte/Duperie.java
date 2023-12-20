package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;

import java.util.List;
import java.util.Scanner;

/**
 * Classe abstraite représentant une carte de type "Duperie" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class Duperie extends Carte {

    /**
     * Constructeur de la carte "Duperie".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Duperie(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Duperie".
     * Le joueur appelant regarde 3 cartes de la Main d'un rival,
     * affiche les 3 premières cartes de la Main du rival, et choisit une carte à ajouter à sa Main.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Non utilisé dans ce contexte.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Regardez 3 cartes de la Main d'un rival
        Joueur rivalChoisi = choisirRival(joueurAppelant);

        if (rivalChoisi != null) {
            List<Carte> cartesMainRival = rivalChoisi.getMain().getCartes();

            // Affichez les 3 premières cartes de la Main du rival
            List<Carte> cartesVisible = cartesMainRival.subList(0, Math.min(cartesMainRival.size(), 3));
            this.renderer.afficherCartes(cartesVisible);

            // Choisissez une carte à ajouter à votre Main
            Carte carteChoisie = choisirCarteDuperie(cartesVisible, joueurAppelant);

            // Ajoutez la carte choisie à votre Main
            if (carteChoisie != null) {
                joueurAppelant.getMain().addCartes(carteChoisie);
            }
        }
    }

    /**
     * Méthode privée pour choisir un rival parmi les joueurs possibles.
     *
     * @param joueurAppelant Le joueur qui choisit le rival.
     * @return Le rival choisi ou null si l'entrée n'est pas valide.
     */
    private Joueur choisirRival(Joueur joueurAppelant) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choisissez un rival (entrez le numéro correspondant) :");
        List<Joueur> joueursPossibles = joueurAppelant.getPartie().getJoueurs();
        joueursPossibles.remove(joueurAppelant); // Exclure le joueur appelant

        // Afficher la liste des joueurs possibles
        for (int i = 0; i < joueursPossibles.size(); i++) {
            System.out.println((i + 1) + ". " + joueursPossibles.get(i).getNom());
        }

        int choix = scanner.nextInt();

        // Retourner le joueur choisi ou null si l'entrée n'est pas valide
        return (choix > 0 && choix <= joueursPossibles.size()) ? joueursPossibles.get(choix - 1) : null;
    }

    /**
     * Méthode privée pour choisir une carte parmi celles affichées pendant la Duperie.
     *
     * @param cartesVisible Les cartes affichées pendant la Duperie.
     * @param joueurAppelant Le joueur qui fait le choix.
     * @return La carte choisie ou null si l'entrée n'est pas valide.
     */
    private Carte choisirCarteDuperie(List<Carte> cartesVisible, Joueur joueurAppelant) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choisissez une carte à ajouter à votre Main (entrez le numéro correspondant) :");

        // Afficher la liste des cartes visibles
        for (int i = 0; i < cartesVisible.size(); i++) {
            System.out.println((i + 1) + ". " + cartesVisible.get(i).getNom());
        }

        int choix = scanner.nextInt();

        // Retourner la carte choisie ou null si l'entrée n'est pas valide
        return (choix > 0 && choix <= cartesVisible.size()) ? cartesVisible.get(choix - 1) : null;
    }
}
