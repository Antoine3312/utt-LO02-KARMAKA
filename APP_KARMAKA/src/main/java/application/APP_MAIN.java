package application;

import application.control.DeroulementJeu;
import application.control.KarmakaCommand;
import application.view.KarmakaCommandController;
import model.carte.Carte;
import model.carte.Incarnation;
import model.carte.Mimetisme;
import model.carte.Transmigration;

import java.util.Arrays;
import java.util.List;

public class APP_MAIN {

    public static void main(String[] args) {
        DeroulementJeu game = new DeroulementJeu(new KarmakaCommand()); // COMMAND PROMPT MODE
//        DeroulementJeu game = new DeroulementJeu(new KarmakaMainFrame()); // GRAPHIC PROMPT MODE
      game.beginGame();


    }
}
