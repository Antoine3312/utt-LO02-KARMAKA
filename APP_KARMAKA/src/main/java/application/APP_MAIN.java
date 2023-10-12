package application;

import application.control.DeroulementJeu;
import application.control.KarmakaCommand;
import application.control.KarmakaMainFrame;

public class APP_MAIN {
//    private static final DeroulementJeu game = new DeroulementJeu(new KarmakaMainFrame());  // GRAPHIC PROMPT MODE
    private static final DeroulementJeu game = new DeroulementJeu(new KarmakaCommand());  // COMMAND PROMPT MODE
    public static void main(String[] args) {
        APP_MAIN.game.beginGame();
    }
}
