package model.joueur;

import application.control.Renderable;
import model.EtatPartie;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.carte.PileCartes;
import model.echelle.Echellon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Joueur {
    protected String nom;
    private PileCartes oeuvre;
    private PileCartes pile;
    private PileCartes vieFutur;
    private List<Carte> main;

    private int nbAnneauxKarmique;


    private boolean hasWon = false;
    private Echellon partie;
    private Collection<Object> oeuvresExposee;

    public Joueur(String nom) {
        this.nom = nom;
    }

    public Joueur(List<Carte> main, int jeton) {
        this.main = main;
        this.nbAnneauxKarmique = jeton;
    }

    public boolean isBot(){
        return false;
    }

    public PileCartes getPile() {
        return pile;
    }

    public List<Carte> getMain() {
        return main;
    }

    public int getNbAnneauxKarmique() {
        return nbAnneauxKarmique;
    }

    @Override
    public String toString() {
        return this.nom;
    }

    public String getNom() {
        return nom;
    }

    public PileCartes getOeuvre() {
        return oeuvre;
    }

    public PileCartes getVieFutur() {
        return vieFutur;
    }

    public void setMain(List<Carte> main) {
        this.main = main;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    public boolean hasWon() {
        return this.hasWon;
    }

    public int seReincarner(NomCouleur couleur) {
        // Calcul du score de la couleur la plus rentable
        int score = 0;
        for(Carte c : this.oeuvre.getCartes()){
            if(c.getCouleur().equals(couleur)){
                score += c.getPoint();
            }
        }
        // Défosser toutes les oeuvres
        EtatPartie partie = EtatPartie.getInstance();
        partie.getFosse().addCartes(this.oeuvre.getCartes());
        // Composition de la nouvelle main
        this.main = new ArrayList<Carte>(this.vieFutur.getCartes());
        this.vieFutur.viderCartes();
        // Composition de la nouvelle
        int carteAPiocher = 6 - this.main.size();
        for(int i = 0; i < carteAPiocher; i++){
            this.pile.getCartes().push(partie.getSource().getCartes().peek());
        }

        return score;
    }

    public void setNbAnneauxKarmique(int nbAnneauxKarmique) {
        this.nbAnneauxKarmique = nbAnneauxKarmique;
    }

    public PileCartes getOeuvreExposee() { return null; }

    public PileCartes getSource() { return null; }

    public int puiserCartesSource(int i) { return i; }

    public void jouerCarte(Carte carteAJouer)  {}

    public Renderable getOeuvres() { return null; }

    public Renderable getDefausse() { return null; }

    public PileCartes getFosse() { return null; }

    public Echellon getPartie() {
        return partie;
    }

    public void defausserCarte(int choixCarte) {
    }

    public List<Carte> getOeuvresExposee() {
        return oeuvresExposee;
    }
}
