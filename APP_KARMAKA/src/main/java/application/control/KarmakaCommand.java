package application.control;

import application.view.KarmakaCommandController;
import model.EtatPartie;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.carte.PileCartes;
import model.joueur.Joueur;
import model.joueur.StyleJeuStrategy;

import java.util.List;
import java.util.Stack;

public class KarmakaCommand
        implements Renderable
{

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
}
