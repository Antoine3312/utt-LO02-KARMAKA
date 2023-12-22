package application.control;

import application.view.KarmakaCommandController;
import model.EtatPartie;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.carte.PileCartes;
import model.joueur.Joueur;
import model.joueur.StyleJeuStrategy;

import java.io.Serializable;
import java.util.List;

/**
 * Controleur gérant le dialogue entre les classes du Model et la vue en mode commande (KarmakaCommandControleur)
 *
 * Contient uniquement des méthodes gérent les erreurs potentielle lié à l'interface (très peu car interface en ligne de commande)
 * (Aurait été plus remplie si nous avions créée une interface graphique, aurait récupérer toutes les erreurs toutes lié à une BD, au droit d'accès, ...)
 */
public class KarmakaCommand implements Renderable, Serializable {
    private static final long serialVersionUID = 2711998155099132322L;

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
        return this.kcc.playNewOrLoadSave();
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
    public String loadSave() {
        return this.kcc.loadSave();
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
    public void afficherInfoJoueurDebutTour(Joueur joueur) {
        this.kcc.afficherInfoJoueurDebutTour(joueur);
    }

    @Override
    public void afficherInfoReincarnation(Joueur joueur) {
        this.kcc.afficherInfoReicarnation(joueur);
    }

    @Override
    public void displayTourInfo(EtatPartie partie) {
        this.kcc.diplayTourInfo(partie);
    }

    @Override
    public void afficherFinDePartie() {
        this.kcc.afficherFinDePartie();
    }

    @Override
    public boolean sauvegarderEtQuitter() {
        return this.kcc.sauvegarderEtQuitter();
    }

    @Override
    public String getNomSauvegarde() {
        return this.kcc.getNomSauvegarde();
    }

    @Override
    public void afficherPartieEnPause() {
        this.kcc.afficherPartieEnPause();
    }
}
