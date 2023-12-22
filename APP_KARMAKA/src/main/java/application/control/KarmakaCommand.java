package application.control;

import application.view.KarmakaCommandController;
import model.EtatPartie;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.carte.PileCartes;
import model.joueur.Joueur;
import model.joueur.StyleJeuStrategy;

import java.util.List;

public class KarmakaCommand implements Renderable {

    private final KarmakaCommandController kcc = new KarmakaCommandController();

    @Override
    public void displayGameStart() {
        this.kcc.displayGameStart();
    }

    @Override
    public void beginDisplayOfTheGame(EtatPartie partie) {
        this.kcc.beginDisplayOfTheGame(partie);
    }

    @Override
    public boolean playNewOrLoadSave() {
        return true;
    }

    @Override
    public int numberOfBot() {
        return this.kcc.numberOfBot();
    }

    @Override
    public String getPlayerName(int numJoueur) {
        return this.kcc.getPlayerName(numJoueur);
    }

    @Override
    public void loadSave() {
        this.kcc.loadSave();
    }

    @Override
    public StyleJeuStrategy getBotDifficulty(String botName) {
        return this.kcc.getBotDifficulty(botName);
    }

    @Override
    public void showPlayer(List<Joueur> joueurs) {
        this.kcc.showPlayer(joueurs);
    }

    @Override
    public NomCouleur choisirCouleur(PileCartes cartes) {
        return this.kcc.choisirCouleur(cartes);
    }

    @Override
    public boolean utiliserJetonKarmique(Joueur joueur) {
        return this.kcc.utiliserJetonKarmique(joueur);
    }

    @Override
    public int combienDeJeton(Joueur joueur) {
        return this.kcc.combienDeJeton(joueur);
    }

    @Override
    public Carte afficherEtChoisirCarteMain(Joueur joueur) {
        return this.kcc.afficherEtChoisirCarteMain(joueur);
    }

    @Override
    public int choisirUtilisation(Carte carte) {
        return this.kcc.choisirUtilisation(carte);
    }

    @Override
    public boolean jouerUneCarteOuNon() {
        return this.kcc.jouerUneCarteOuNon();
    }

    @Override
    public void afficherCartes(List<Carte> cartes) {
        this.kcc.afficherCartes(cartes);
    }

    @Override
    public void displayErrorMessage(String s) {
        this.kcc.displayErrorMessage(s);
    }

    @Override
    public List<Carte> choisirDeuxCarte(List<Carte> cartes) {
        return this.kcc.choisirDeuxCarte(cartes);
    }

    @Override
    public Carte choisirUneCarte(List<Carte> Cartes) {
        return this.kcc.choisirUneCarte(Cartes);
    }

    @Override
    public void displayMessage(String message) {
        this.kcc.displayMessage(message);
    }

    @Override
    public void afficherInfoJoueur(Joueur joueur) {
        this.kcc.afficherInfoJoueur(joueur);
    }

    @Override
    public void afficherInfoReincarnation(Joueur joueur) {
        this.kcc.afficherInfoReicarnation(joueur);
    }
}
