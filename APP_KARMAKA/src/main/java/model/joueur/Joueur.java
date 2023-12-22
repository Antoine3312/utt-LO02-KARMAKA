package model.joueur;

import application.control.Renderable;
import model.EtatPartie;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.carte.PileCartes;
import model.echelle.Echellon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * La classe Joueur représente un joueur dans le jeu.
 * Elle implémente l'interface Serializable.
 */
public class Joueur implements Serializable {
    // Le nom du joueur
    protected String nom;

    // La pile d'oeuvres du joueur
    private PileCartes oeuvre;

    // La pile de cartes du joueur
    private PileCartes pile;

    // La pile de la Vie Future du joueur
    private PileCartes vieFutur;

    // La main du joueur
    private List<Carte> main;

    // Le nombre d'anneaux karmiques du joueur
    private int nbAnneauxKarmique;

    // Indique si le joueur a remporté la partie
    private boolean hasWon = false;

    /**
     * Constructeur de la classe Joueur avec un nom donné.
     *
     * @param nom Le nom du joueur.
     */
    public Joueur(String nom) {
        this.nom = nom;
        this.oeuvre = new PileCartes();
        this.pile = new PileCartes();
        this.vieFutur = new PileCartes();
        this.main = new ArrayList<>();
        this.nbAnneauxKarmique = 0;
    }

    /**
     * Constructeur de la classe Joueur avec une main et des jetons donnés.
     *
     * @param main   La main du joueur.
     * @param jeton  Le nombre d'anneaux karmiques du joueur.
     */
    public Joueur(List<Carte> main, int jeton) {
        this.main = main;
        this.nbAnneauxKarmique = jeton;
    }

    /**
     * Méthode pour vérifier si le joueur est un bot.
     *
     * @return true si le joueur est un bot, sinon false.
     */
    public boolean isBot() {
        return false;
    }

    /**
     * Méthode pour obtenir la pile de cartes du joueur.
     *
     * @return La pile de cartes du joueur.
     */
    public PileCartes getPile() {
        return pile;
    }

    /**
     * Méthode pour obtenir la main du joueur.
     *
     * @return La main du joueur.
     */
    public List<Carte> getMain() {
        return main;
    }

    /**
     * Méthode pour obtenir le nombre d'anneaux karmiques du joueur.
     *
     * @return Le nombre d'anneaux karmiques du joueur.
     */
    public int getNbAnneauxKarmique() {
        return nbAnneauxKarmique;
    }

    /**
     * Méthode pour obtenir la représentation textuelle du joueur.
     *
     * @return Le nom du joueur.
     */
    @Override
    public String toString() {
        return this.nom;
    }

    /**
     * Méthode pour obtenir le nom du joueur.
     *
     * @return Le nom du joueur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode pour obtenir la pile d'oeuvres du joueur.
     *
     * @return La pile d'oeuvres du joueur.
     */
    public PileCartes getOeuvre() {
        return oeuvre;
    }

    /**
     * Méthode pour obtenir la pile de la Vie Future du joueur.
     *
     * @return La pile de la Vie Future du joueur.
     */
    public PileCartes getVieFutur() {
        return vieFutur;
    }

    /**
     * Méthode pour définir la main du joueur.
     *
     * @param main La nouvelle main du joueur.
     */
    public void setMain(List<Carte> main) {
        this.main = main;
    }

    /**
     * Méthode pour définir si le joueur a remporté la partie.
     *
     * @param hasWon true si le joueur a remporté la partie, sinon false.
     */
    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    /**
     * Méthode pour vérifier si le joueur a remporté la partie.
     *
     * @return true si le joueur a remporté la partie, sinon false.
     */
    public boolean hasWon() {
        return this.hasWon;
    }

    /**
     * Méthode pour définir le nombre d'anneaux karmiques du joueur.
     *
     * @param nbAnneauxKarmique Le nouveau nombre d'anneaux karmiques du joueur.
     */
    public void setNbAnneauxKarmique(int nbAnneauxKarmique) {
        this.nbAnneauxKarmique = nbAnneauxKarmique;
    }

    /**
     * Méthode pour définir la pile de cartes du joueur.
     *
     * @param pile La nouvelle pile de cartes du joueur.
     */
    public void setPile(PileCartes pile) {
        this.pile = pile;
    }
}
