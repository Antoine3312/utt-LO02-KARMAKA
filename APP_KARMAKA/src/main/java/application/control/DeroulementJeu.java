package application.control;

import model.joueur.Joueur;
import model.joueur.Ordinateur;
import model.joueur.StyleJeuStrategy;

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
            Joueur[] joueurs = createPlayer(nbBot);
        } else {
            loadSave();
        }


//        this.renderer.beginDisplayOfTheGame();
    }

    private Joueur[] createPlayer(int nbBot) {
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
            case 1:
                playerName = this.renderer.getPlayerName(1);
                j1 = new Joueur(playerName);
                niveauBot = this.renderer.getBotDifficulty();
                j2 = new Ordinateur("Bot", niveauBot);
                break;
            case 2:
                niveauBot = this.renderer.getBotDifficulty();
                j1 = new Ordinateur("Bot1", niveauBot);
                niveauBot = this.renderer.getBotDifficulty();
                j2 = new Ordinateur("Bot2", niveauBot);
                break;
            default:
                break;
        }
        return new Joueur[]{j1, j2};
    }

    private void loadSave() {
        this.renderer.loadSave();
    }


}
