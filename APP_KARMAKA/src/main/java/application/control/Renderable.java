package application.control;

import model.EtatPartie;
import model.joueur.StyleJeuStrategy;

public interface Renderable {

    public void displayGameStart();

    public void beginDisplayOfTheGame(EtatPartie partie);

    boolean playNewOrLoadSave();

    int numberOfBot();

    String getPlayerName(int numJoueur);

    void loadSave();

    StyleJeuStrategy getBotDifficulty();
}
