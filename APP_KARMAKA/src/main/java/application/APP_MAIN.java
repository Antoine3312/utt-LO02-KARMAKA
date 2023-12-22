package application;

import application.control.DeroulementJeu;
import application.control.KarmakaCommand;
import application.view.KarmakaCommandController;
import model.EtatPartie;
import model.GestionnaireSauvegardePartie;
import model.carte.Carte;
import model.carte.Incarnation;
import model.carte.Mimetisme;
import model.carte.Transmigration;
import model.joueur.Joueur;

import java.util.Arrays;
import java.util.List;

public class APP_MAIN {

    public static void main(String[] args) {
        DeroulementJeu game = new DeroulementJeu(new KarmakaCommand()); // COMMAND PROMPT MODE
//        DeroulementJeu game = new DeroulementJeu(new KarmakaMainFrame()); // GRAPHIC PROMPT MODE
      game.beginGame();

//        GestionnaireSauvegardePartie gsp = new GestionnaireSauvegardePartie();
//        EtatPartie partie = EtatPartie.getInstance();
//        partie.setNumTour(10);
//        partie.setJoueur1(new Joueur("Antoine"));
//        gsp.sauvegarderPartie("test", partie);
////        EtatPartie.setInstance(gsp.chargerPartie("test"));
////        EtatPartie partie = EtatPartie.getInstance();
////        System.out.println(partie.getJoueur1());



    }
}
