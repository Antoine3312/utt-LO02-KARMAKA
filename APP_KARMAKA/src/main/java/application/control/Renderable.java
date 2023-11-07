package application.control;

import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.StyleJeuStrategy;

import java.util.List;

public interface Renderable {

    public void displayGameStart();

    public void beginDisplayOfTheGame(EtatPartie partie);

    boolean playNewOrLoadSave();

    int numberOfBot();

    String getPlayerName(int numJoueur);

    void loadSave();

    StyleJeuStrategy getBotDifficulty(String botName);

    void showPlayer(List<Joueur> joueurs);
}
