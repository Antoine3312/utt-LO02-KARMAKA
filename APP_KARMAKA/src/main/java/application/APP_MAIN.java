package application;

import application.control.DeroulementJeu;
import application.control.KarmakaCommand;
import application.control.KarmakaMainFrame;
import model.joueur.Joueur;
import model.joueur.Ordinateur;
import model.joueur.Playable;
import model.joueur.StrategyDebutant;

public class APP_MAIN {
    public static void main(String[] args) {
//        DeroulementJeu game = new DeroulementJeu(new KarmakaCommand()); // COMMAND PROMPT MODE
        DeroulementJeu game = new DeroulementJeu(new KarmakaMainFrame()); // GRAPHIC PROMPT MODE
        game.beginGame();
        Playable j1 = new Joueur();
        Playable j2 = new Ordinateur(new StrategyDebutant());
    }
}
