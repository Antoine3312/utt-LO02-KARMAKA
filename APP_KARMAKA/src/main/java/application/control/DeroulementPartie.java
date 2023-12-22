package application.control;

import model.EtatPartie;
import model.GestionnaireSauvegardePartie;
import model.carte.*;
import model.echelle.EchelleKarmique;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * La classe DeroulementPartie gère le déroulement d'une partie du jeu, coordonnant les différentes étapes
 * telles que l'initialisation du jeu, le déroulement des tours, la réincarnation des joueurs, le jeu de cartes, etc.
 * Elle interagit avec l'interface utilisateur via un objet Renderable et utilise la classe ActionJouer pour effectuer
 * les actions de jeu.
 *
 */
public class DeroulementPartie {

    private final DeroulementJeu dj;
    private Renderable renderer;
    private EtatPartie partie = EtatPartie.getInstance();;
    private ActionJouer actionJouer;

    /**
     * Constructeur de la classe DeroulementPartie.
     *
     * @param renderer L'objet Renderable utilisé pour afficher des messages dans l'interface utilisateur.
     * @param dj       L'objet DeroulementJeu auquel cette instance est associée.
     */
    public DeroulementPartie(Renderable renderer, DeroulementJeu dj) {
        this.renderer = renderer;
        this.actionJouer = new ActionJouer(this.renderer);
        this.dj = dj;
    }

    /**
     * Démarre une nouvelle partie du jeu avec les joueurs spécifiés.
     *
     * @param joueurs La liste des joueurs participant à la nouvelle partie.
     */
    public void startNewGame(List<Joueur> joueurs) {
        this.initGame(joueurs);
        this.initHands();
        this.initPile();
        this.jouerPartie();
    }


    /**
     * Démarre une nouvelle partie du jeu à partir d'une sauvegarde existante.
     * Utilisé lors du chargement d'une partie existante.
     *
     * @param partie L'état de partie chargé à partir de la sauvegarde.
     */
    public void startNewGame(EtatPartie partie){
        this.partie.setInstance(partie);
        this.jouerPartie();
    }

    /**
     * Initialise les différent élèments d'une partie (Echelle karmique, pile, source, fosse, etc)
     *
     * @param joueurs La liste des joueurs participant à la nouvelle partie.
     */
    private void initGame(List<Joueur> joueurs) {
        EchelleKarmique echelle = new EchelleKarmique();
        echelle.getBousier().addPlayers(joueurs);
        PileCartes source = new PileCartes();
        source.getCartes().addAll(loadSource());
        PileCartes fosse = new PileCartes();
        int numTour = 1;
        this.partie.init(echelle,joueurs.get(0),joueurs.get(1),source,fosse,numTour);
    }

    /**
     * Gère le déroulement de la partie en bouclant sur les tours des joueurs jusqu'à ce qu'un joueur remporte la partie
     * ou que la partie soit mise en pause.
     */
    private void jouerPartie(){
        boolean partieEnPause = false;
        while (!this.partie.getJoueur1().hasWon() && !this.partie.getJoueur2().hasWon()){
            this.renderer.displayTourInfo(this.partie);
            if((!(this.partie.getJoueur1() instanceof Ordinateur) || !(this.partie.getJoueur2() instanceof Ordinateur)) && this.renderer.sauvegarderEtQuitter()){
                String fileName = this.renderer.getNomSauvegarde();
                GestionnaireSauvegardePartie gsp = new GestionnaireSauvegardePartie();
                gsp.sauvegarderPartie(fileName, partie);
                partieEnPause = true;
                break;
            }

            this.jouerTour(this.partie.getJoueur1());
            if(this.partie.getJoueur1().hasWon()){
                break;
            }
            this.jouerTour(this.partie.getJoueur2());
            this.partie.setNumTour(this.partie.getNumTour()+1);
        }
        if(partieEnPause){
            this.partieEnPause();
        } else {
            this.finDePartie();
        }
    }

    /**
     * Affiche les messages de fin de partie et démarre une nouvelle partie.
     */
    private void finDePartie() {
        this.renderer.afficherFinDePartie();
        this.dj.beginGame();
    }

    /**
     * Affiche le message de partie en pause et démarre une nouvelle partie.
     */
    private void partieEnPause(){
        this.renderer.afficherPartieEnPause();
        this.dj.beginGame();
    }

    /**
     * Gère le tour d'un joueur, en exécutant les actions appropriées en fonction du type de joueur.
     *
     * @param joueur Le joueur dont c'est le tour.
     */
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

    /**
     * Réincarne un joueur en affichant les informations nécessaires à l'interface utilisateur et en effectuant la réincarnation.
     *
     * @param joueur Le joueur à réincarner.
     */
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

    /**
     * Gère le jeu d'un joueur en piochant une carte et en effectuant une action en fonction des choix de l'interface utilisateur.
     *
     * @param joueur Le joueur dont c'est le tour.
     */
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



    /**
     * Initialise les mains des joueurs en distribuant quatre cartes à chacun depuis la pile source.
     */
    private void initHands() {
        List<Joueur> joueurs = Arrays.asList(this.partie.getJoueur1(), this.partie.getJoueur2());
        for (Joueur j : joueurs){
            for (int i = 0; i<4; i++){
                j.getMain().add(this.partie.getSource().getCartes().pop());
            }
        }
    }

    /**
     * Initialise la pile de chaque joueur en y plaçant deux cartes depuis la pile source.
     */
    private void initPile() {
        List<Joueur> joueurs = Arrays.asList(this.partie.getJoueur1(), this.partie.getJoueur2());
        for (Joueur j : joueurs){
            for (int i = 0; i<2; i++){
                j.getPile().getCartes().push(this.partie.getSource().getCartes().pop());
            }
        }
    }

    /**
     * Initialise la source.
     *
     * @return la source contenant toutes les cartes.
     */
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
