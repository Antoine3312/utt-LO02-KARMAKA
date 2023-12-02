package application.control;

import model.EtatPartie;
import model.echelle.EchelleKarmique;
import model.joueur.Joueur;
import model.joueur.Ordinateur;
import model.joueur.StyleJeuStrategy;

import java.util.Arrays;
import java.util.List;

public class DeroulementJeu {
    private Renderable renderer;
    private DeroulementPartie dp;

    public DeroulementJeu(Renderable renderer) {
        this.renderer = renderer;
        this.dp = new DeroulementPartie(this.renderer);
    }

    public void beginGame(){
        this.renderer.displayGameStart();
        boolean newGame = this.renderer.playNewOrLoadSave();
        if(newGame){
            int nbBot = this.renderer.numberOfBot();
            List<Joueur> joueurs = createPlayer(nbBot);
            this.renderer.showPlayer(joueurs);
            System.out.println(joueurs);
            this.dp.startNewGame(joueurs);
        } else {
            loadSave();
        }
    }



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
                j2 = new Ordinateur("Bot", niveauBot);
                break;
            case 2:
                niveauBot = this.renderer.getBotDifficulty("Bot1");
                j1 = new Ordinateur("Bot1", niveauBot);
                niveauBot = this.renderer.getBotDifficulty("Bot2");
                j2 = new Ordinateur("Bot2", niveauBot);
                break;
            default:
                break;
        }
        return Arrays.asList(j1, j2);
    }

    private void loadSave() {
        this.renderer.loadSave();
    }


}
