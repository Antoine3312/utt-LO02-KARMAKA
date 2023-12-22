package application;

import application.control.DeroulementJeu;
import application.control.KarmakaCommand;

public class APP_MAIN {

    public static void main(String[] args) {
        DeroulementJeu game = new DeroulementJeu(new KarmakaCommand()); // COMMAND PROMPT MODE
//        DeroulementJeu game = new DeroulementJeu(new KarmakaMainFrame()); // GRAPHIC PROMPT MODE
      game.beginGame();


    }
}
