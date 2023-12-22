package application.control;

import model.EtatPartie;
import model.carte.*;
import model.echelle.EchelleKarmique;
import model.echelle.Echellon;
import model.echelle.NomPalier;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

        this.jouerPartie();
    }
    private void jouerPartie(){
        while (this.partie.getJoueur1().hasWon() || this.partie.getJoueur2().hasWon()){
//            this.jouerTour(this.partie.getJoueur1());
            if(this.partie.getJoueur1().hasWon()){
                break;
            }
//            this.jouerTour(this.partie.getJoueur2());
        }
//        this.finDePartie();
    }

    private void jouerTour(Joueur joueur) {
        if(joueur instanceof Ordinateur){
            ((Ordinateur) joueur).executeTour();
        } else {
            if (joueur.getMain().isEmpty() && joueur.getPile().getCartes().isEmpty()){
                this.reincarner(joueur);
            } else {
//                this.jouer(joueur);
            }
        }

    }

    private void reincarner(Joueur joueur) {
        NomCouleur couleurLaPlusRentable = this.renderer.choisirCouleur(joueur.getOeuvre());
        int score = this.effectuerReincarnationDe(joueur, couleurLaPlusRentable);
        Echellon echellon = this.partie.getEchelle().getEchellonOf(joueur);
        if( this.renderer.utiliserJetonKarmique(joueur) ){
            int nbAnneauxJouer = this.renderer.combienDeJeton(joueur);
            score += nbAnneauxJouer;
            joueur.setNbAnneauxKarmique(joueur.getNbAnneauxKarmique() - nbAnneauxJouer);
        }
        if(score >= echellon.getPtsNecessairePourMonter()){
            if(this.partie.getEchelle().monterCategorie(joueur).equals(NomPalier.SINGE)){
                joueur.setHasWon(true);
            };
        }
    }

    private int effectuerReincarnationDe(Joueur joueur, NomCouleur couleur) {
        // Calcul du score de la couleur la plus rentable
        int score = 0;
        for(Carte c : joueur.getOeuvre().getCartes()){
            if(c.getCouleur().equals(couleur)){
                score += c.getPoint();
            }
        }
        // Défosser toutes les oeuvres
        partie.getFosse().addCartes(joueur.getOeuvre().getCartes());

        // Composition de la nouvelle main
        joueur.setMain( new ArrayList<Carte>(joueur.getVieFutur().getCartes()));
        joueur.getVieFutur().viderCartes();

        // Composition de la nouvelle
        int carteAPiocher = 6 - joueur.getMain().size();
        for(int i = 0; i < carteAPiocher; i++){
            joueur.getPile().getCartes().push(partie.getSource().getCartes().peek());
        }

        return score;
    }

    private void jouer(Joueur joueur) {
        boolean jouerCarte = true;
        if(!joueur.getPile().getCartes().isEmpty()){
            joueur.getMain().add(this.partie.getSource().getCartes().pop());
            jouerCarte = this.renderer.jouerUneCarteOuNon();
        }
        if(jouerCarte){
            Carte carte = this.renderer.afficherEtChoisirCarteMain(joueur);
            int utilisation = this.renderer.choisirUtilisation(carte);
            switch (utilisation){
//                case DeroulementPartie.UTILISATIONPOUVOIR -> carte.jouerPouvoir();
//                case DeroulementPartie.UTILISATIONFUTUR -> carte.jouerFutur();
//                case DeroulementPartie.UTILISATIONPOINT -> carte.jouerPoint();
            }
        }
    }



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
        List<Carte> jeu = Arrays.asList(
                //cartes ici
        );
        Collections.shuffle(jeu);
        return jeu;
    }
}
