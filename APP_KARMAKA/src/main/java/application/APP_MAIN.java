package application;

import application.control.DeroulementJeu;
import application.control.KarmakaCommand;
import application.control.KarmakaMainFrame;
import application.view.ColoredText;
import application.view.KarmakaCommandController;
import model.carte.Carte1;
import model.carte.Carte2;
import model.carte.NomCouleur;
import model.carte.PileCartes;
import model.echelle.EchelleKarmique;
import model.joueur.Joueur;

import java.util.Arrays;

public class APP_MAIN {

    public static void main(String[] args) {
        DeroulementJeu game = new DeroulementJeu(new KarmakaCommand()); // COMMAND PROMPT MODE
//        DeroulementJeu game = new DeroulementJeu(new KarmakaMainFrame()); // GRAPHIC PROMPT MODE
        game.beginGame();
    }
}
