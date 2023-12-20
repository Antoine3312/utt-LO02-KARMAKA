package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Scanner;

/**
 * Classe abstraite représentant la carte "Crise" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class Crise extends Carte {

    /**
     * Constructeur de la carte "Crise".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Crise(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Crise".
     * Le rival de votre choix défausse une de ses Oeuvres.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur cible du pouvoir.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Le rival de votre choix défausse une de ses Oeuvres
        Joueur rivalChoisi = choisirRival(joueurAppelant);

        if (rivalChoisi != null) {
            defausserOeuvre(rivalChoisi);
        }
    }

    /**
     * Méthode privée pour choisir un rival.
     *
     * @param joueurAppelant Le joueur qui fait le choix.
     * @return Le rival choisi.
     */
    private Joueur choisirRival(Joueur joueurAppelant) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choisissez un rival (entrez le numéro correspondant) :");
        List<Joueur> joueursPossibles = joueurAppelant.getPartie().getJoueurs();
        joueursPossibles.remove(joueurAppelant); // Exclure le joueur appelant

        // Affichage des choix de rivaux possibles
        for (int i = 0; i < joueursPossibles.size(); i++) {
            System.out.println((i + 1) + ". " + joueursPossibles.get(i).getNom());
        }

        // Lecture de l'entrée de l'utilisateur pour choisir un rival
        int choix = scanner.nextInt();
        return (choix > 0 && choix <= joueursPossibles.size()) ? joueursPossibles.get(choix - 1) : null;
    }

    /**
     * Méthode privée pour défausser une œuvre d'un joueur.
     *
     * @param joueur Le joueur dont une œuvre doit être défaussée.
     */
    private void defausserOeuvre(Joueur joueur) {
        // Vérifier si le joueur a des œuvres exposées
        if (!joueur.getOeuvresExposee().isEmpty()) {
            // Obtenir la liste des œuvres exposées par le joueur
            List<Carte> oeuvres = joueur.getOeuvresExposee();

            // Vérifier si le joueur est un ordinateur
            if (joueur instanceof Ordinateur) {
                // Si le joueur est un ordinateur, il choisit automatiquement une œuvre à défausser
                Carte oeuvreADefausser = oeuvres.get(0);
                joueur.getPartie().getDefausse().addCartes(oeuvreADefausser);
                joueur.getOeuvresExposee().remove(oeuvreADefausser);
            } else {
                // Si le joueur est humain, il choisit l'œuvre à défausser
                joueur.getPartie().getRenderer().afficherCartes(oeuvres);
                System.out.println("Choisissez une œuvre à défausser (entrez le numéro correspondant) :");
                int choix = joueur.getPartie().getRenderer().demanderEntier();

                // Vérifier si le choix est valide
                if (choix > 0 && choix <= oeuvres.size()) {
                    // Défausser l'œuvre choisie par le joueur humain
                    Carte oeuvreADefausser = oeuvres.get(choix - 1);
                    joueur.getPartie().getDefausse().addCartes(oeuvreADefausser);
                    joueur.getOeuvresExposee().remove(oeuvreADefausser);
                }
            }
        }

    }

}

