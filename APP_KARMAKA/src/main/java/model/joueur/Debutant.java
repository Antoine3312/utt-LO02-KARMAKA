package model.joueur;

import application.control.ActionJouer;
import application.control.Renderable;
import model.carte.Carte;
import model.carte.NomCouleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Debutant implements StyleJeuStrategy {

    public ActionJouer actionJouer = new ActionJouer();

    public Random r = new Random();
    private Renderable renderer;

    @Override
    public void jouerTour(Joueur joueur, Renderable renderer) {
        this.renderer = renderer;
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
        boolean jouerCarte = (this.r.nextInt(1) == 1);
        if(jouerCarte) {
            Carte careAJouer = joueur.getMain().get(this.r.nextInt(joueur.getMain().size()));
            this.actionJouer.jouer(joueur, true, careAJouer, (r.nextInt(3))+1);
        } else {
            this.actionJouer.jouer(joueur, false, null, 0);
            this.renderer.displayErrorMessage(String.format("%s décide de passer son tour. Attention, il prépare sûrement un plan diabolique ...", joueur.getNom()));
        }
    }
}
