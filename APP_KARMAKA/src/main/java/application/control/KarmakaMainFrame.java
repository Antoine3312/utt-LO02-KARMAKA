package application.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import application.view.KarmakaMainFrameController;
import model.EtatPartie;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.carte.PileCartes;
import model.joueur.Joueur;
import model.joueur.StyleJeuStrategy;

import java.util.List;

public class KarmakaMainFrame extends Application
        implements Renderable
{
    private KarmakaMainFrameController kmfc;

    private Stage primaryStage;


    public static void runApp() {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        try {

            FXMLLoader loader = new FXMLLoader(
                    KarmakaMainFrameController.class.getResource("/application/view/KarmakaMainFramePane.fxml"));

            BorderPane root = loader.load();

            Scene scene = new Scene(root, root.getPrefWidth()+20, root.getPrefHeight()+10);
            scene.getStylesheets().add(KarmakaMainFrameController.class.getResource("KarmakaMainFramePane.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setTitle("KARMAKA");
            this.primaryStage.setResizable(false);
            this.kmfc = loader.getController();
            this.kmfc.initContext(primaryStage, this);
            this.kmfc.displayDialog();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayGameStart() {
       KarmakaMainFrame.runApp();
    }

    @Override
    public void beginDisplayOfTheGame(EtatPartie partie) {

    }

    @Override
    public boolean playNewOrLoadSave() {
        return false;
    }

    @Override
    public int numberOfBot() {
        return 0;
    }

    @Override
    public String getPlayerName(int numJoueur) {
        return null;
    }

    @Override
    public void loadSave() {

    }

    @Override
    public StyleJeuStrategy getBotDifficulty(String botName) {
        return null;
    }

    @Override
    public void showPlayer(List<Joueur> joueurs) {

    }

    @Override
    public NomCouleur choisirCouleur(PileCartes cartes) {
        return null;
    }

    @Override
    public boolean utiliserJetonKarmique(Joueur joueur) {
        return false;
    }

    @Override
    public int combienDeJeton(Joueur joueur) {
        return 0;
    }

    @Override
    public Carte afficherEtChoisirCarte(Joueur joueur) {
        return null;
    }

    @Override
    public int choisirUtilisation(Carte carte) {
        return 0;
    }

    @Override
    public boolean jouerUneCarteOuNon() {
        return true;
    }
}
