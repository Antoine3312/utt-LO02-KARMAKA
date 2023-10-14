package application;

import application.control.DeroulementJeu;
import application.control.KarmakaCommand;
import model.joueur.Joueur;
import model.joueur.Ordinateur;
import model.joueur.Debutant;

public class APP_MAIN {
    public static void main(String[] args) {
        DeroulementJeu game = new DeroulementJeu(new KarmakaCommand()); // COMMAND PROMPT MODE
//        DeroulementJeu game = new DeroulementJeu(new KarmakaMainFrame()); // GRAPHIC PROMPT MODE
        game.beginGame();
        Joueur j1 = new Joueur();
        Joueur j2 = new Ordinateur(new Debutant());
    }
}
