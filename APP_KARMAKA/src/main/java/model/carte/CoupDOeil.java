package model.carte;

import application.control.Renderable;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.Collections;
import java.util.List;

/**
 * Classe abstraite représentant la carte "Coup d'Œil" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public abstract class CoupDOeil extends Carte {

    /**
     * Constructeur de la carte "Coup d'Œil".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public CoupDOeil(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Coup d'Œil".
     * Regardez la Main d'un rival. Vous pouvez ensuite jouer une autre carte.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur cible du pouvoir.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Regardez la Main d'un rival
        Joueur rivalChoisi = choisirRival(joueurAppelant);

        if (rivalChoisi != null) {
            regarderMain(rivalChoisi);

            // Vous pouvez ensuite jouer une autre carte
            if (joueurAppelant instanceof Ordinateur) {
                jouerAutreCarte(joueurAppelant);
            } else {
                this.renderer.afficherCartes(joueurAppelant.getMain());
                Carte carteAJouer = this.renderer.choisirUneCarte(joueurAppelant.getMain());

                if (carteAJouer != null) {
                    joueurAppelant.jouerCarte(carteAJouer);
                }
            }
        }
    }

    /**
     * Méthode abstraite pour copier le pouvoir de la carte.
     *
     * @param joueurActif Le joueur qui utilise le pouvoir.
     * @param joueurCible Le joueur cible du pouvoir.
     */
    protected abstract void copierPouvoir(Joueur joueurActif, Joueur joueurCible);

    // Méthode pour choisir un rival
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

    // Méthode pour choisir un rival (cas de l'ordinateur)
    private Joueur choisirRivalOrdinateur(List<Joueur> joueursPossibles) {
        // Choix aléatoire d'un rival (ordinateur)
        Collections.shuffle(joueursPossibles);
        return joueursPossibles.get(0);
    }

    // Méthode pour choisir un rival (cas de l'humain)
    private Joueur choisirRivalHumain(List<Joueur> joueursPossibles) {
        // Affichage des choix de rivaux possibles
        this.renderer.afficherChoixRivaux(joueursPossibles);

        // Sélection du rival par l'humain
        return this.renderer.choisirUnRival(joueursPossibles);
    }

    // Méthode pour regarder la Main d'un rival
    private void regarderMain(Joueur rival) {
        List<Carte> cartesMainRival = rival.getMain();
        this.renderer.afficherCartes(cartesMainRival);
    }

    // Méthode pour jouer une autre carte (cas de l'ordinateur)
    private void jouerAutreCarte(Joueur joueur) {
        List<Carte> cartesJouables = joueur.getMain();

        if (!cartesJouables.isEmpty()) {
            Carte carteAJouer = cartesJouables.get(0); // Choix aléatoire de la première carte jouable
            joueur.jouerCarte(carteAJouer);
        }
    }
}
