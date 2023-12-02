package application.control;

import model.EtatPartie;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.carte.PileCartes;
import model.joueur.Joueur;
import model.joueur.StyleJeuStrategy;

import java.util.List;

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

    Carte afficherEtChoisirCarte(Joueur joueur);

    int choisirUtilisation(Carte carte);

    boolean jouerUneCarteOuNon();
}
