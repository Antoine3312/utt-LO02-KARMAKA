package application.control;

import model.EtatPartie;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.carte.PileCartes;
import model.echelle.Echellon;
import model.echelle.NomPalier;
import model.joueur.Joueur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * La classe ActionJouer représente les actions de jeu effectuées par les joueurs dans le cadre d'une partie.
 * Ces actions incluent la réincarnation d'un joueur, le jeu de cartes avec différentes utilisations (pouvoir, futur, points).
 * Les méthodes de cette classe interagissent avec les différents éléments du modèle de jeu, tels que les cartes, les joueurs,
 * l'échelle spirituelle, etc.
 *
 */
public class ActionJouer implements Serializable {

    private static final long serialVersionUID = 2711998155099132322L;
    private final Renderable renderer;
    private EtatPartie partie = EtatPartie.getInstance();

    public static final int UTILISATIONPOUVOIR = 1;
    public static final int UTILISATIONFUTUR = 2;
    public static final int UTILISATIONPOINT = 3;

    public ActionJouer(Renderable renderer) {
        this.renderer = renderer;
    }

    /**
     * Réincarne un joueur en fonction de la couleur la plus rentable et de certaines conditions.
     *
     * @param joueur             Le joueur à réincarner.
     * @param couleurLaPlusRentable La couleur la plus rentable pour la réincarnation.
     * @param utiliserSesAnneaux Indique si le joueur doit utiliser ses anneaux karmiques.
     * @param nbAnneauxJouer     Le nombre d'anneaux karmiques à jouer.
     */
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
                    EtatPartie.getInstance().getJoueur(joueur).setHasWon(true);
                };
            } else {
                joueur.setNbAnneauxKarmique(joueur.getNbAnneauxKarmique() + 1);
                this.renderer.displayErrorMessage(String.format("%s n'a pas assez de point pour monter de catégorie (%s sur %s), il recoit alors 1 anneaux Karmique en compensation", joueur.getNom(), score, EtatPartie.getInstance().getEchelle().getEchellonOf(joueur).getPtsNecessairePourMonter()));
            }
        } else {
            this.renderer.displayErrorMessage(String.format("%s n'a joué aucune carte pour leurs points. Il reste donc sur le même Echellon ...", joueur.getNom()));
        }
    }


    /**
     * Effectue une action de jeu en utilisant une carte spécifiée.
     *
     * @param joueurAppelant Le joueur qui effectue l'action.
     * @param carteJouer      La carte à jouer.
     * @param utilisation     Le type d'utilisation de la carte (pouvoir, futur, points).
     */
    public void jouer(Joueur joueurAppelant, Carte carteJouer, int utilisation) {
        List<Joueur> joueursPartie = Arrays.asList(this.partie.getJoueur1(), this.partie.getJoueur2());
        Joueur jouerReceveur = null;
        for(Joueur j : joueursPartie){
            if(j != joueurAppelant) {
                jouerReceveur = j;
            }
        }
        System.out.println();
        switch (utilisation){
            case ActionJouer.UTILISATIONPOUVOIR -> carteJouer.jouerPouvoir(joueurAppelant, jouerReceveur);
            case ActionJouer.UTILISATIONFUTUR -> carteJouer.jouerFutur(joueurAppelant);
            case ActionJouer.UTILISATIONPOINT -> carteJouer.jouerPoint(joueurAppelant);
        }
        joueurAppelant.getMain().remove(carteJouer);
    }

    /**
     * Effectue la réincarnation d'un joueur en fonction de la couleur spécifiée.
     * Cette méthode est utilisée en interne par la méthode reincarner.
     *
     * @param joueur  Le joueur à réincarner.
     * @param couleur La couleur pour laquelle calculer le score de réincarnation.
     * @return Le score de réincarnation pour la couleur spécifiée.
     */
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
        joueur.getOeuvre().viderCartes();

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
