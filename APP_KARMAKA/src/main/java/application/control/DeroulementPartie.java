package application.control;

import model.EtatPartie;
import model.carte.*;
import model.echelle.EchelleKarmique;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeroulementPartie {

    private Renderable renderer;
    private EtatPartie partie;
    private ActionJouer actionJouer;

    public DeroulementPartie(Renderable renderer) {
        this.renderer = renderer;
        this.actionJouer = new ActionJouer(this.renderer);
    }

    public void startNewGame(List<Joueur> joueurs) {
        this.partie = EtatPartie.getInstance();
        this.initGame(joueurs);
        this.initHands();
        this.initPile();
        this.jouerPartie();
    }

    private void initGame(List<Joueur> joueurs) {
        EchelleKarmique echelle = new EchelleKarmique();
        echelle.getBousier().addPlayers(joueurs);
        PileCartes source = new PileCartes();
        source.getCartes().addAll(loadSource());
        PileCartes fosse = new PileCartes();
        int numTour = 1;
        this.partie.init(echelle,joueurs.get(0),joueurs.get(1),source,fosse,numTour);
    }
    private void jouerPartie(){
        while (!this.partie.getJoueur1().hasWon() && !this.partie.getJoueur2().hasWon()){
            System.out.println("Jouer 1 a gagner : "+EtatPartie.getInstance().getJoueur1().hasWon());
            System.out.println("Jouer 2 a gagner : "+EtatPartie.getInstance().getJoueur2().hasWon());
            this.renderer.displayTourInfo(this.partie);
            this.jouerTour(this.partie.getJoueur1());
            if(this.partie.getJoueur1().hasWon()){
                break;
            }
            this.jouerTour(this.partie.getJoueur2());
            this.partie.setNumTour(this.partie.getNumTour()+1);
        }
        this.finDePartie();
    }

    private void finDePartie() {
        System.out.println("fin de partie");
        System.out.println(this.partie.getJoueur1()+" a gagné:"+this.partie.getJoueur1().hasWon());
        System.out.println(this.partie.getJoueur2()+" a gagné:"+this.partie.getJoueur2().hasWon());
    }

    private void jouerTour(Joueur joueur) {
        this.renderer.afficherInfoJoueurDebutTour(joueur);
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
        this.renderer.afficherInfoReincarnation(joueur);
        NomCouleur couleurLaPlusRentable = null;
        if(!joueur.getOeuvre().getCartes().isEmpty()) {
            couleurLaPlusRentable = this.renderer.choisirCouleur(joueur.getOeuvre());
        }
        if(joueur.getNbAnneauxKarmique()>0 && this.renderer.utiliserJetonKarmique(joueur)) {
            this.actionJouer.reincarner(joueur, couleurLaPlusRentable, true, this.renderer.combienDeJeton(joueur));
        } else {
            this.actionJouer.reincarner(joueur, couleurLaPlusRentable, false, 0);
        }
    }

    private void jouer(Joueur joueur) {
        if(!joueur.getPile().getCartes().isEmpty()){
            this.renderer.displayMessage(String.format("%s pioche une carte.", joueur.getNom()));
            joueur.getMain().add(joueur.getPile().getCartes().pop());
        }
        if(this.renderer.jouerUneCarteOuNon()){
            Carte carteAJouer = this.renderer.afficherEtChoisirCarteMain(joueur);
            this.actionJouer.jouer(joueur, carteAJouer, this.renderer.choisirUtilisation(carteAJouer));
        }
    }



    private void initHands() {
        List<Joueur> joueurs = Arrays.asList(this.partie.getJoueur1(), this.partie.getJoueur2());
        for (Joueur j : joueurs){
            for (int i = 0; i<1; i++){
                j.getMain().add(this.partie.getSource().getCartes().pop());
            }
        }
    }

    private void initPile() {
        List<Joueur> joueurs = Arrays.asList(this.partie.getJoueur1(), this.partie.getJoueur2());
        for (Joueur j : joueurs){
            for (int i = 0; i<0; i++){
                j.getPile().getCartes().push(this.partie.getSource().getCartes().pop());
            }
        }
    }

    private List<Carte> loadSource(){
        List<Carte> jeu = Arrays.asList(
                // Cartes Bleues
                new Transmigration(this.renderer),
                new Transmigration(this.renderer),
                new Transmigration(this.renderer),
                new Destinee(this.renderer),
                new Destinee(this.renderer),
                new Destinee(this.renderer),
                new ReveBrises(this.renderer),
                new ReveBrises(this.renderer),
                new ReveBrises(this.renderer),
                new Duperie(this.renderer),
                new Duperie(this.renderer),
                new Deni(this.renderer),
                new Deni(this.renderer),
                new Deni(this.renderer),
                new Vol(this.renderer),
                // Cartes Vertes
                new Lendemain(this.renderer),
                new Lendemain(this.renderer),
                new Lendemain(this.renderer),
                new Recyclage(this.renderer),
                new Recyclage(this.renderer),
                new Recyclage(this.renderer),
                new Longevite(this.renderer),
                new Longevite(this.renderer),
                new Longevite(this.renderer),
                new Semis(this.renderer),
                new Semis(this.renderer),
                new Semis(this.renderer),
                new Voyage(this.renderer),
                new Voyage(this.renderer),
                new Jubile(this.renderer),
                new Jubile(this.renderer),
                // Cartes Rouges
                new Panique(this.renderer),
                new Panique(this.renderer),
                new Panique(this.renderer),
                new DernierSouffle(this.renderer),
                new DernierSouffle(this.renderer),
                new DernierSouffle(this.renderer),
                new Crise(this.renderer),
                new Crise(this.renderer),
                new Crise(this.renderer),
                new Roulette(this.renderer),
                new Roulette(this.renderer),
                new Roulette(this.renderer),
                new Fournaise(this.renderer),
                new Fournaise(this.renderer),
                new Fournaise(this.renderer),
                new Vengeance(this.renderer),
                new Vengeance(this.renderer),
                new Bassesse(this.renderer),
                new Bassesse(this.renderer),
                // Cartes Mosaiques
                new Incarnation(this.renderer),
                new Incarnation(this.renderer),
                new Incarnation(this.renderer),
                new Incarnation(this.renderer),
                new Incarnation(this.renderer),
                new Mimetisme(this.renderer),
                new Mimetisme(this.renderer)
        );
        Collections.shuffle(jeu);
        return jeu;
    }
}
