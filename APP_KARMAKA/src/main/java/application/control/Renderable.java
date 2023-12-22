package application.control;

import model.EtatPartie;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.carte.PileCartes;
import model.joueur.Joueur;
import model.joueur.StyleJeuStrategy;

import java.util.List;
import java.util.ServiceLoader;
import java.util.Stack;

public interface Renderable {

    public void displayGameStart();

    public void beginDisplayOfTheGame(EtatPartie partie);

    boolean playNewOrLoadSave();

    int numberOfBot();

    String getPlayerName(int numJoueur);

    void loadSave();

    StyleJeuStrategy getBotDifficulty(String botName);

    void showPlayer(List<Joueur> joueurs);

    NomCouleur choisirCouleur(PileCartes cartes);

    boolean utiliserJetonKarmique(Joueur joueur);

    int combienDeJeton(Joueur joueur);

    Carte afficherEtChoisirCarteMain(Joueur joueur);

    int choisirUtilisation(Carte carte);

    boolean jouerUneCarteOuNon();

    void afficherCartes(List<Carte> cartes);

    void displayErrorMessage(String s);

    List<Carte> choisirDeuxCarte(List<Carte> cartes);

    Carte choisirUneCarte(List<Carte> cartes);

    void displayMessage(String message);

    void afficherInfoJoueur(Joueur joueur);

    void afficherInfoReincarnation(Joueur joueur);
}

