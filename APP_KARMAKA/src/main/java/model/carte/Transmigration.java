package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;

public class Transmigration extends Carte {
    public Transmigration(Renderable renderable) {
        super(renderable);
        this.point = 1;
        this.couleur = NomCouleur.BLEU;
    }

    @Override
    public void jouerPouvoir (Joueur joueurAppelant, Joueur joueurReceveur) {
        Carte carteChoisi = null;
        List<Carte> carteVieFutur = joueurAppelant.getVieFutur().getCartes();
        if(joueurAppelant instanceof Ordinateur){
            carteChoisi = carteVieFutur.get(new Random().nextInt(carteVieFutur.size()));
        } else {
            this.renderer.afficherCartes(carteVieFutur);
            carteChoisi = this.renderer.choisirUneCarteVieFutur(carteVieFutur);
        }
        joueurAppelant.getVieFutur().getCartes().remove(carteChoisi);
        joueurAppelant.getMain().add(carteChoisi);

    }
}
