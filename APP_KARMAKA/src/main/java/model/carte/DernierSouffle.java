package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.Random;

public class DernierSouffle extends Carte{


    public DernierSouffle(Renderable renderer) {
        super(renderer);
        this.nom = "Dernier Souffle";
        this.point = 1;
        this.couleur = NomCouleur.ROUGE;
    }

    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        if(!joueurReceveur.getMain().isEmpty()){
            Carte carteChoisie = null;
            if(!(joueurReceveur instanceof Ordinateur)){
                carteChoisie = this.renderer.choisirUneCarte(joueurReceveur.getMain());
            } else {
                Random r = new Random();
                carteChoisie = joueurReceveur.getMain().get(r.nextInt(joueurReceveur.getMain().size()));
            }
            EtatPartie.getInstance().getFosse().getCartes().add(carteChoisie);
            joueurReceveur.getMain().remove(carteChoisie);
        } else {
            this.renderer.displayErrorMessage("Impossible : Le joueur rival n'a aucune carte en main.");
        }
    }
}
