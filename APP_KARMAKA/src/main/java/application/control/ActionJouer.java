package application.control;

import model.EtatPartie;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.carte.PileCartes;
import model.echelle.Echellon;
import model.echelle.NomPalier;
import model.joueur.Joueur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActionJouer {

    private final Renderable renderer;
    private EtatPartie partie = EtatPartie.getInstance();

    public static final int UTILISATIONPOUVOIR = 1;
    public static final int UTILISATIONFUTUR = 2;
    public static final int UTILISATIONPOINT = 3;

    public ActionJouer(Renderable renderer) {
        this.renderer = renderer;
    }

    public void reincarner(Joueur joueur, NomCouleur couleurLaPlusRentable, boolean utiliserSesAnneaux, int nbAnneauxJouer) {
        int score = this.effectuerReincarnationDe(joueur, couleurLaPlusRentable);
        if (!(couleurLaPlusRentable == null)){
            Echellon echellon = this.partie.getEchelle().getEchellonOf(joueur);
            if( utiliserSesAnneaux ){
                score += nbAnneauxJouer;
                joueur.setNbAnneauxKarmique(joueur.getNbAnneauxKarmique() - nbAnneauxJouer);
            }
            if(score >= echellon.getPtsNecessairePourMonter()){
                if(this.partie.getEchelle().monterCategorie(joueur).equals(NomPalier.SINGE)){
                    joueur.setHasWon(true);
                };
            } else {
                joueur.setNbAnneauxKarmique();
            }
        }
    }

    public void jouer(Joueur joueurAppelant, boolean jouerUneCarte, Carte carteJouer, int utilisation) {
        if(!joueurAppelant.getPile().getCartes().isEmpty()){
            joueurAppelant.getMain().add(joueurAppelant.getPile().getCartes().pop());
        }
        if(jouerUneCarte){
            List<Joueur> joueursPartie = Arrays.asList(this.partie.getJoueur1(), this.partie.getJoueur2());
            Joueur jouerReceveur = null;
            for(Joueur j : joueursPartie){
                if(j != joueurAppelant) {
                    jouerReceveur = j;
                }
            }
            switch (utilisation){
                case ActionJouer.UTILISATIONPOUVOIR -> carteJouer.jouerPouvoir(joueurAppelant, jouerReceveur);
                case ActionJouer.UTILISATIONFUTUR -> carteJouer.jouerFutur(joueurAppelant);
                case ActionJouer.UTILISATIONPOINT -> carteJouer.jouerPoint(joueurAppelant);
            }
            joueurAppelant.getMain().remove(carteJouer);
        }
    }

    public void creerNouvellePileEtMain(Joueur joueur){
        PileCartes nouvellePile = new PileCartes();
        List<Carte> nouvelleMain = joueur.getVieFutur().getCartes();
        if(nouvelleMain.size()<6){
            for(int i=0; i<6 - nouvelleMain.size(); i++){
                nouvellePile.getCartes().push(this.partie.getSource().getCartes().pop());
            }
        }
        List<Joueur> joueurs = Arrays.asList(this.partie.getJoueur1(), this.partie.getJoueur2());
        for(Joueur j : joueurs){
            if (j == joueur){
                j.setPile(nouvellePile);
                j.setMain(nouvelleMain);
            }
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
        partie.getFosse().getCartes().addAll(joueur.getOeuvre().getCartes());

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
}
