package model.joueur;

import application.control.ActionJouer;
import application.control.Renderable;
import model.carte.Carte;
import model.carte.NomCouleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Debutant implements StyleJeuStrategy {

    public ActionJouer actionJouer;

    public Random r = new Random();
    private Renderable renderer;

    @Override
    public void jouerTour(Joueur joueur, Renderable renderer) {
        this.renderer = renderer;
        this.actionJouer = new ActionJouer(this.renderer);
        if (joueur.getMain().isEmpty() && joueur.getPile().getCartes().isEmpty()){
            this.reincarner(joueur);
        } else {
            this.jouer(joueur);
        }

    }

    private void reincarner(Joueur joueur){
        List<String> couleurs = new ArrayList<>(joueur.getOeuvre().getCouleursInStack());
        NomCouleur couleurLaPlusRentable = NomCouleur.valueOf(couleurs.get(r.nextInt(couleurs.size())));
        boolean utiliserAnneaux = this.r.nextInt(2) == 1;
        if(utiliserAnneaux){
            this.actionJouer.reincarner(joueur, couleurLaPlusRentable, true, (this.r.nextInt(joueur.getNbAnneauxKarmique())) + 1);
        } else {
            this.actionJouer.reincarner(joueur, couleurLaPlusRentable, false, 0);
        }
    }

    private void jouer(Joueur joueur){
        if(!joueur.getPile().getCartes().isEmpty()){
            joueur.getMain().add(joueur.getPile().getCartes().pop());
        }
        boolean jouerCarte = !(this.r.nextInt(4) == 1);
        if(jouerCarte) {
            Carte careAJouer = joueur.getMain().get(this.r.nextInt(joueur.getMain().size()));
            this.actionJouer.jouer(joueur, careAJouer, (r.nextInt(3))+1);
        }
    }
}
