package application.control;

import application.view.KarmakaCommandController;
import model.EtatPartie;
import model.joueur.StyleJeuStrategy;

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
    public StyleJeuStrategy getBotDifficulty() {
        return this.kcc.getBotDifficulty();
    }
}
