package application.control;

import model.EtatPartie;
import model.carte.Carte;
import model.carte.Carte1;
import model.carte.Carte2;
import model.carte.PileCartes;
import model.echelle.EchelleKarmique;
import model.joueur.Joueur;

import java.util.Arrays;
import java.util.List;

public class DeroulementPartie {

    private Renderable renderer;
    private EtatPartie partie;

    public DeroulementPartie(Renderable renderer) {
        this.renderer = renderer;
    }

    public void startNewGame(List<Joueur> joueurs) {
        this.partie = this.partie.getInstance();
        this.initGame(joueurs);
        this.initHands(joueurs);
    }

    private void initGame(List<Joueur> joueurs) {
        EchelleKarmique echelle = new EchelleKarmique();
        echelle.getBousier().addPlayers(joueurs);
        PileCartes source = new PileCartes();
        source.addCartes(loadSource());
        PileCartes fosse = new PileCartes();
        int numTour = 0;
        this.partie.init(echelle,joueurs.get(0),joueurs.get(1),source,fosse,numTour);
    }

    private void initHands(List<Joueur> joueurs) {

    }
    
    private List<Carte> loadSource(){
        return Arrays.asList(new Carte1(), new Carte2());
    }
}
