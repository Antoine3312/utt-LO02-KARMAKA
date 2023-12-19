package application.control;

import model.EtatPartie;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.carte.PileCartes;
import model.joueur.Joueur;
import model.joueur.StyleJeuStrategy;

import java.util.List;
import java.util.ServiceLoader;

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

    void afficherCartes(List<Carte> carteVieFutur);

    Carte choisirUneCarteVieFutur(List<Carte> carteVieFutur);

    Carte choisirUneCarteOeuvreExposee(List<Carte> carteOeuvreExposee);

    Carte choisirUneCarteSource(List<Carte> carteSourceJoueurAppelant);

    Carte choisirUneCarte(List<Carte> Cartes);

    Carte getsCartes(List<Carte> Cartes);

    Carte getCartes(List<Carte> Cartes);

    Carte getCartesJouables(List<Carte> CartesJouables);

    <E> List<E> getCartes();

    PileCartes getExposition();

    ServiceLoader<Object> stream();

