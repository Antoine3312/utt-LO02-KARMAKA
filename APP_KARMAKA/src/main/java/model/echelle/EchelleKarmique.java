package model.echelle;

import model.joueur.Joueur;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * La classe EchelleKarmique représente l'échelle karmique du jeu.
 * Elle gère les échelons (Echellon) et les paliers (NomPalier) associés aux joueurs.
 * Elle implémente Serializable pour permettre la sérialisation.
 */
public class EchelleKarmique implements Serializable {

    // Numéro de version pour la sérialisation
    private static final long serialVersionUID = 2711998155099132322L;

    // Échelons correspondant aux différents paliers
    private final Echellon bousier = new Echellon(4, NomPalier.BOUSIER);
    private final Echellon serpent = new Echellon(5, NomPalier.SERPENT);
    private final Echellon loup = new Echellon(6, NomPalier.LOUP);
    private final Echellon singe = new Echellon(7, NomPalier.SINGE);

    /**
     * Renvoie l'échelon associé au palier "BOUSIER".
     *
     * @return L'échelon "BOUSIER".
     */
    public Echellon getBousier() {
        return bousier;
    }

    /**
     * Renvoie l'échelon associé au palier "SERPENT".
     *
     * @return L'échelon "SERPENT".
     */
    public Echellon getSerpent() {
        return serpent;
    }

    /**
     * Renvoie l'échelon associé au palier "LOUP".
     *
     * @return L'échelon "LOUP".
     */
    public Echellon getLoup() {
        return loup;
    }

    /**
     * Renvoie l'échelon associé au palier "SINGE".
     *
     * @return L'échelon "SINGE".
     */
    public Echellon getSinge() {
        return singe;
    }

    /**
     * Renvoie l'échelon associé à un joueur.
     *
     * @param joueur Le joueur dont on veut connaître l'échelon.
     * @return L'échelon du joueur, ou null s'il n'appartient à aucun échelon.
     */
    public Echellon getEchellonOf(Joueur joueur) {
        // Liste des échelons
        List<Echellon> echelons = Arrays.asList(this.serpent, this.bousier, this.singe, this.loup);
        // Parcours des échelons pour trouver celui du joueur
        for (Echellon echellon : echelons) {
            if (echellon.getJoueurs().contains(joueur)) {
                return echellon;
            }
        }
        return null;
    }

    /**
     * Permet à un joueur de monter d'un palier sur l'échelle karmique.
     *
     * @param joueur Le joueur qui monte d'un palier.
     * @return Le nouveau palier du joueur après la montée.
     */
    public NomPalier monterCategorie(Joueur joueur) {
        NomPalier current = null;
        switch (this.getEchellonOf(joueur).getNom()) {
            case BOUSIER:
                this.bousier.getJoueurs().remove(joueur);
                this.serpent.getJoueurs().add(joueur);
                current = NomPalier.SERPENT;
                break;
            case SERPENT:
                this.serpent.getJoueurs().remove(joueur);
                this.loup.getJoueurs().add(joueur);
                current = NomPalier.LOUP;
                break;
            case LOUP:
                this.loup.getJoueurs().remove(joueur);
                this.singe.getJoueurs().add(joueur);
                current = NomPalier.SINGE;
                break;
            case SINGE:
                current = NomPalier.SINGE;
                break;
        }
        return current;
    }

    /**
     * Redéfinition de la méthode toString pour obtenir une représentation textuelle de l'échelle karmique.
     *
     * @return Une chaîne représentant l'échelle karmique.
     */
    @Override
    public String toString() {
        return String.format("bousier(%s), Serpent(%s), Loup(%s), Singe(%s)",
                this.bousier.getJoueurs(), this.serpent.getJoueurs(), this.loup.getJoueurs(), this.singe.getJoueurs());
    }
}
