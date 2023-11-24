package application.control;

import model.EtatPartie;
import model.carte.Carte;
import model.carte.Carte1;
import model.carte.Carte2;
import model.carte.PileCartes;
import model.echelle.EchelleKarmique;
import model.echelle.Echellon;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.Arrays;
import java.util.List;

public class DeroulementPartie {

    private Renderable renderer;
    private EtatPartie partie;

    private static final int UTILISATIONPOUVOIR = 1;
    private static final int UTILISATIONFUTUR = 2;
    private static final int UTILISATIONPOINT = 3;

    public DeroulementPartie(Renderable renderer) {
        this.renderer = renderer;
    }

    public void startNewGame(List<Joueur> joueurs) {
        this.partie = EtatPartie.getInstance();
        this.initGame(joueurs);
        this.initHands();
        this.initPile();
    }

    private void initGame(List<Joueur> joueurs) {
        EchelleKarmique echelle = new EchelleKarmique();
        echelle.getBousier().addPlayers(joueurs);
        PileCartes source = new PileCartes();
        source.addCartes(loadSource());
        PileCartes fosse = new PileCartes();
        int numTour = 0;
        this.partie.init(echelle,joueurs.get(0),joueurs.get(1),source,fosse,numTour);

//        this.jouerPartie();
    }
/*
    private void jouerPartie(){
        while (this.partie.getJoueur1().hasWon() || this.partie.getJoueur2().hasWon()){
            this.jouerTour(this.partie.getJoueur1());
            this.jouerTour(this.partie.getJoueur2());
        }
        this.finDePartie();
    }

    private void jouerTour(Joueur joueur) {
        if(joueur instanceof Ordinateur){
            ((Ordinateur) joueur).executeTour();
        } else {
            if (joueur.getMain().isEmpty() && joueur.getPile().getCartes().isEmpty()){
                this.reincarner(joueur);
            } else {
                this.jouer(joueur);
            }
        }

    }

    private void reincarner(Joueur joueur) {
        int score = joueur.reincarner();
        Echellon echellon = this.partie.getEchelle().getEchellonOf(joueur);
        if( this.renderer.utiliserJetonKarmique() ){
            score += this.renderer.combienDeJeton();
        }
        if(score >= echellon.getPtsNecessairePourMonter()){
            this.partie.getEchelle().monterCategorie(joueur);
        }
    }

    private void jouer(Joueur joueur) {
        if(!joueur.getPile().getCartes().isEmpty()){
            joueur.getMain().add(this.partie.getSource().getCartes().pop());
        }
        Carte carte = this.renderer.afficherEtChoisirCarte();
        int utilisation = this.renderer.choisirUtilisation(carte);
        switch (utilisation){
            case DeroulementPartie.UTILISATIONPOUVOIR -> carte.jouerPouvoir();
            case DeroulementPartie.UTILISATIONFUTUR -> carte.jouerFutur();
            case DeroulementPartie.UTILISATIONPOINT -> carte.jouerPoint();
        }
    }*/

    private void initHands() {
        List<Joueur> joueurs = Arrays.asList(this.partie.getJoueur1(), this.partie.getJoueur2());
        for (Joueur j : joueurs){
            for (int i = 0; i<4; i++){
                j.getMain().add(this.partie.getSource().getCartes().pop());
            }
        }
    }

    private void initPile() {
        List<Joueur> joueurs = Arrays.asList(this.partie.getJoueur1(), this.partie.getJoueur2());
        for (Joueur j : joueurs){
            for (int i = 0; i<2; i++){
                j.getPile().getCartes().push(this.partie.getSource().getCartes().pop());
            }
        }
    }

    private List<Carte> loadSource(){
        return Arrays.asList(new Carte1(), new Carte2());
    }
}
