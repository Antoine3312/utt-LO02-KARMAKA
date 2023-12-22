package application.control;

import model.EtatPartie;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.carte.PileCartes;
import model.joueur.Joueur;
import model.joueur.StyleJeuStrategy;

import java.util.List;

/**
 * Contrat d'interface, contenant toutes les méthodes nécessaire à l'affichage du jeu.
 *
 * Pour chaque type de réprésentation de l'application, implémenter cette interface.
 */
public interface Renderable {

    /**
     *  Affiche le message de démarrage du jeu
     */
    public void displayGameStart();

    /**
     *  Annonce le début d'une partie
     */
    public void beginDisplayOfTheGame(EtatPartie partie);

    /**
     *  Demande à l'utilisateur s'il préfère lancer une partie ou en charger une.
     */
    boolean playNewOrLoadSave();


    /**
     * Demande à l'utilisateur combien il veut de bot
     */
    int numberOfBot();


    /**
     * Demande le nom d'un joueur
     *
     * @param numJoueur son numéro
     * @return son nom
     */
    String getPlayerName(int numJoueur);


    /**
     * Demande à l'utilisateur quel sauvegarde de partie il veut charger.
     *
     * @return le nom du fichier voulu
     */
    String loadSave();


    /**
     * Demande le difficulté voulu d'un bot
     *
     * @param botName le nom du bot
     * @return la difficulté voulu
     */
    StyleJeuStrategy getBotDifficulty(String botName);

    /**
     * Affiche les jours d'une parties (leurs noms)
     *
     * @param joueurs les joueurs
     */
    void showPlayer(List<Joueur> joueurs);

    /**
     * Choisi une couleur contenu dans une pile de carte
     *
     * @param cartes les cartes
     * @return la couleur
     */
    NomCouleur choisirCouleur(PileCartes cartes);

    /**
     *  Demande à un joueur s'il souhaite utiliser ses jetons karmique lorsqu'il se réincarne
     *
     * @param joueur le joueur
     * @return true s'il le souhaite, false sinon
     */
    boolean utiliserJetonKarmique(Joueur joueur);

    /**
     * Demande à un joueur combien il souhaite utiliser de jetons karmique lorsqu'il se réincarne
     * @param joueur le joueur
     * @return le nombre de jeton à utiliser
     */
    int combienDeJeton(Joueur joueur);

    /**
     * Affiche les cartes de la main d'un joueur et lui demande d'en choisir une
     * @param joueur le joueur
     * @return la carte choisi
     */
    Carte afficherEtChoisirCarteMain(Joueur joueur);

    /**
     * Choisi l'utilisation d'une carte
     * @param carte la carte
     * @return un entier correspondant à son utilisation (point, pouvoir ou futur)
     */
    int choisirUtilisation(Carte carte);

    /**
     * Demande à l'utilisateur s'il souhaite jouer une carte ou non
     * @return true s'il le souhaite false sinon
     */
    boolean jouerUneCarteOuNon();

    /**
     * Affiche des cartes à l'écran
     * @param cartes les cartes
     */
    void afficherCartes(List<Carte> cartes);

    /**
     * Affichage un message d'erreur/d'échec (en rouge)
     * @param s le message
     */
    void displayErrorMessage(String s);

    /**
     * Demande à l'utilisateur de choisir 2 cartes dans un paquet de cartes
     * @param cartes le paquet de cartes
     * @return les 2 cartes
     */
    List<Carte> choisirDeuxCarte(List<Carte> cartes);

    /**
     * Demande à l'utilisateur de choisir une carte parmis un paquet de cartes
     * @param cartes le paquets
     * @return la carte choisie
     */
    Carte choisirUneCarte(List<Carte> cartes);

    /**
     * Affiche un message de succès/d'information (couleur basique de l'IHM)
     * @param message
     */
    void displayMessage(String message);

    /**
     * Affiche les information d'un début de tour d'un jouer (son oeuvre exposes, combien il a de carte dans sa pile, etc)
     * @param joueur le joueur
     */
    void afficherInfoJoueurDebutTour(Joueur joueur);

    /**
     * Affiche des infos à la réincarnation d'un joueur (les cartes qu'il a dans ses oeuvres, etc)
     * @param joueur
     */
    void afficherInfoReincarnation(Joueur joueur);

    /**
     * Afficher les information d'un tour (numéro de tour actuel, etc)
     * @param partie
     */
    void displayTourInfo(EtatPartie partie);

    /**
     * Affiche les messages de fin de partie
     */
    void afficherFinDePartie();

    /**
     * Demande à l'utilisateur s'il souhaite continuer de jouer ou sauvegarder sa partie et quitter.
     * @return true s'il veut quitter et sauvegarder, false s'il veut continuer de jouer
     */
    boolean sauvegarderEtQuitter();

    /**
     *  Demande le nom de la sauvegarde que l'utilisateur veut charger
     * @return le nom de la sauvegarde
     */
    String getNomSauvegarde();

    /**
     * Affiche un message lorsque la partie est mis en pause (lorsque l'utilisateur sauvegarde et quitte)
     */
    void afficherPartieEnPause();
}

