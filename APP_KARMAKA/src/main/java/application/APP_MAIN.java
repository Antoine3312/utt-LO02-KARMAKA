package application;

import application.control.DeroulementJeu;
import application.control.KarmakaCommand;
import application.control.KarmakaMainFrame;

public class APP_MAIN {

    private static DeroulementJeu game = new DeroulementJeu(new KarmakaMainFrame());  // GRAPHIC PROMPT MODE
//    private static DeroulementJeu game = new DeroulementJeu(new KarmakaCommand());  // COMMAND PROMPT MODE
    public static void main(String[] args) {
       /* Ordinateur bot = new Ordinateur(new StrategyExpert());
        bot.executeTour();*/

        APP_MAIN.game.beginGame();

    }
}
