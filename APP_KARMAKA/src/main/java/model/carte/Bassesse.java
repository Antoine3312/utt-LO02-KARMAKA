package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.Collections;
import java.util.List;

/**
 * Classe abstraite représentant une carte de type "Bassesse". Cette classe hérite de la classe abstraite "Carte".
 */
public abstract class Bassesse extends Carte {

    /**
     * Constructeur de la carte "Bassesse".
     *
     * @param renderable L'objet renderable associé à la carte.
     */
    public Bassesse(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
    }

    /**
     * Implémentation de la méthode jouerPouvoir() définie dans l'interface PouvoirCarte.
     *
     * @param joueurAppelant Le joueur qui utilise le pouvoir de la carte.
     * @param joueurReceveur Le joueur ciblé par le pouvoir de la carte.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Défaussez au hasard 2 cartes de la Main d'un rival
        Joueur rivalChoisi = choisirRival(joueurAppelant);

        if (rivalChoisi != null) {
            defausserAuHasard(rivalChoisi, 2);
        }
    }

    /**
     * Méthode pour choisir un rival.
     *
     * @param joueurAppelant Le joueur qui choisit le rival.
     * @return Le joueur rival choisi.
     */
    private Joueur choisirRival(Joueur joueurAppelant) {
        List<Joueur> joueursPossibles = joueurAppelant.getPartie().getJoueurs();
        joueursPossibles.remove(joueurAppelant); // Exclure le joueur appelant

        // Si le joueur est un ordinateur, il choisit automatiquement un rival
        if (joueurAppelant instanceof Ordinateur) {
            return choisirRivalOrdinateur(joueursPossibles);
        }

        // Si le joueur est humain, il choisit le rival
        return choisirRivalHumain(joueursPossibles);
    }

    /**
     * Méthode pour choisir un rival (cas de l'ordinateur).
     *
     * @param joueursPossibles La liste des joueurs parmi lesquels choisir un rival.
     * @return Le joueur rival choisi.
     */
    private Joueur choisirRivalOrdinateur(List<Joueur> joueursPossibles) {
        // Choix aléatoire d'un rival (ordinateur)
        Collections.shuffle(joueursPossibles);
        return joueursPossibles.get(0);
    }

    /**
     * Méthode pour choisir un rival (cas de l'humain).
     *
     * @param joueursPossibles La liste des joueurs parmi lesquels choisir un rival.
     * @return Le joueur rival choisi.
     */
    private Joueur choisirRivalHumain(List<Joueur> joueursPossibles) {
        // Affichage des choix de rivaux possibles
        this.renderer.afficherChoixRivaux(joueursPossibles);

        // Sélection du rival par l'humain
        return this.renderer.choisirUnRival(joueursPossibles);
    }

    /**
     * Méthode pour défausser au hasard un certain nombre de cartes de la Main d'un joueur.
     *
     * @param joueur        Le joueur dont les cartes doivent être défaussées.
     * @param nombreCartes  Le nombre de cartes à défausser.
     */
    private void defausserAuHasard(Joueur joueur, int nombreCartes) {
        List<Carte> cartesMain = joueur.getMain().getCartes();

        // Vérification si le nombre de cartes à défausser est inférieur ou égal au nombre de cartes en main
        if (nombreCartes <= cartesMain.size()) {
            // Défausse aléatoire de cartes
            Collections.shuffle(cartesMain);
            List<Carte> cartesADefausser = cartesMain.subList(0, nombreCartes);

            // Ajout des cartes à la défausse et suppression de la main
            joueur.getPartie().getDefausse().addCartes(cartesADefausser);
            cartesMain.removeAll(cartesADefausser);
        }
    }
}
