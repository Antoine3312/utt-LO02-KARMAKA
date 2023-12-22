package application.control;

import model.EtatPartie;
import model.GestionnaireSauvegardePartie;
import model.echelle.EchelleKarmique;
import model.joueur.Joueur;
import model.joueur.Ordinateur;
import model.joueur.StyleJeuStrategy;

import java.util.Arrays;
import java.util.List;

/**
 * La classe DeroulementJeu gère le déroulement global d'une partie du jeu, en prenant en charge
 * le démarrage d'une nouvelle partie ou le chargement d'une sauvegarde existante.
 * Elle interagit avec l'interface utilisateur via un objet Renderable et utilise la classe DeroulementPartie
 * pour coordonner les différentes étapes d'une partie.
 *
 */
public class DeroulementJeu {
    private Renderable renderer;
    private DeroulementPartie dp;

    /**
     * Constructeur de la classe DeroulementJeu.
     *
     * @param renderer L'objet Renderable utilisé pour afficher des messages dans l'interface utilisateur.
     */
    public DeroulementJeu(Renderable renderer) {
        this.renderer = renderer;
        this.dp = new DeroulementPartie(this.renderer, this);
    }

    /**
     * Lance le début d'une nouvelle partie en demandant à l'utilisateur s'il souhaite commencer une nouvelle partie
     * ou charger une sauvegarde existante. En fonction de la réponse, la méthode initialise une nouvelle partie avec
     * des joueurs humains ou charge une partie sauvegardée.
     */
    public void beginGame(){
        this.renderer.displayGameStart();
        boolean newGame = this.renderer.playNewOrLoadSave();
        if(newGame){
            int nbBot = this.renderer.numberOfBot();
            List<Joueur> joueurs = createPlayer(nbBot);
            this.renderer.showPlayer(joueurs);
            this.dp.startNewGame(joueurs);
        } else {
            String nomSauvegarde = loadSave();
            GestionnaireSauvegardePartie gsp = new GestionnaireSauvegardePartie();
            EtatPartie partie = gsp.chargerPartie(nomSauvegarde);
            this.dp.startNewGame(partie);
        }
    }


    /**
     * Crée une liste de joueurs en fonction du nombre de joueurs humains et d'ordinateur spécifié par l'utilisateur.
     *
     * @param nbBot Le nombre de joueurs automatiques.
     * @return Une liste de joueurs créée en fonction des choix de l'utilisateur.
     */
    private List<Joueur> createPlayer(int nbBot) {
        Joueur j1 = null;
        Joueur j2 = null;
        StyleJeuStrategy niveauBot;
        String playerName;
        switch (nbBot){
            case 0:
                playerName = this.renderer.getPlayerName(1);
                j1 = new Joueur(playerName);
                playerName = this.renderer.getPlayerName(2);
                j2 = new Joueur(playerName);
                break;
            case 1:
                playerName = this.renderer.getPlayerName(1);
                j1 = new Joueur(playerName);
                niveauBot = this.renderer.getBotDifficulty("Bot");
                j2 = new Ordinateur("Bot", niveauBot, this.renderer);
                break;
            case 2:
                niveauBot = this.renderer.getBotDifficulty("Bot1");
                j1 = new Ordinateur("Bot1", niveauBot, this.renderer);
                niveauBot = this.renderer.getBotDifficulty("Bot2");
                j2 = new Ordinateur("Bot2", niveauBot, this.renderer);
                break;
            default:
                break;
        }
        return Arrays.asList(j1, j2);
    }

    /**
     * Charge une sauvegarde existante à partir de son nom.
     *
     * @return Le nom de la sauvegarde à charger.
     */
    private String loadSave() {
        return this.renderer.loadSave();
    }


}
