package model.echelle;

import application.control.Renderable;
import model.carte.PileCartes;
import model.joueur.Joueur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Echellon représente un échelon de l'échelle karmique du jeu.
 * Elle gère les joueurs associés à cet échelon, les points nécessaires pour monter à l'échelon supérieur,
 * le palier auquel cet échelon appartient, et d'autres éléments liés à l'échelon.
 * Elle implémente Serializable pour permettre la sérialisation.
 */
public class Echellon implements Serializable {

    // Numéro de version pour la sérialisation
    private static final long serialVersionUID = 2711998155099132322L;

    // Liste des joueurs associés à cet échelon
    private List<Joueur> joueurs;

    // Points nécessaires pour monter à l'échelon supérieur
    private int ptsNecessairePourMonter;

    // Le palier auquel cet échelon appartient
    private NomPalier nom;

    /**
     * Constructeur de la classe Echellon.
     *
     * @param ptsNecessairePourMonter Points nécessaires pour monter à l'échelon supérieur.
     * @param nom                      Le palier auquel cet échelon appartient.
     */
    public Echellon(int ptsNecessairePourMonter, NomPalier nom) {
        this.ptsNecessairePourMonter = ptsNecessairePourMonter;
        this.nom = nom;
        this.joueurs = new ArrayList<>();
    }

    /**
     * Ajoute une liste de joueurs à cet échelon.
     *
     * @param joueurs La liste de joueurs à ajouter à cet échelon.
     */
    public void addPlayers(List<Joueur> joueurs) {
        this.joueurs.addAll(joueurs);
    }

    /**
     * Renvoie la liste des joueurs associés à cet échelon.
     *
     * @return La liste des joueurs associés à cet échelon.
     */
    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    /**
     * Renvoie le nombre de points nécessaires pour monter à l'échelon supérieur.
     *
     * @return Le nombre de points nécessaires pour monter à l'échelon supérieur.
     */
    public int getPtsNecessairePourMonter() {
        return ptsNecessairePourMonter;
    }

    /**
     * Renvoie le palier auquel cet échelon appartient.
     *
     * @return Le palier auquel cet échelon appartient.
     */
    public NomPalier getNom() {
        return nom;
    }

    /**
     * Renvoie la source associée à cet échelon.
     * Cette méthode peut renvoyer null, car l'échelon ne gère pas une source de cartes.
     *
     * @return La source associée à cet échelon ou null.
     */
    public PileCartes getSource() {
        return null;
    }

    /**
     * Renvoie la défause associée à cet échelon.
     * Cette méthode peut renvoyer null, car l'échelon ne gère pas une défause de cartes.
     *
     * @return La défause associée à cet échelon ou null.
     */
    public PileCartes getDefausse() {
        return null;
    }

    /**
     * Renvoie le rendu associé à cet échelon.
     * Cette méthode peut renvoyer null, car l'échelon ne gère pas un rendu spécifique.
     *
     * @return Le rendu associé à cet échelon ou null.
     */
    public Renderable getRenderer() {
        return null;
    }
}
