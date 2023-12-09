package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;

public class Vol extends Carte {
    public Vol(Renderable renderable) {
        super(renderable);
        this.point = 3;
        this.couleur = NomCouleur.BLEU;
    }

    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        Carte oeuvreChoisi = null;
        List<Carte> carteOeuvreExposee = joueurAppelant.getOeuvreExposee().getCartes();
        if (joueurAppelant instanceof Ordinateur) {
            oeuvreChoisi = carteOeuvreExposee.get(new Random().nextInt(carteOeuvreExposee.size()));
        } else {
            this.renderer.afficherCartes(carteOeuvreExposee);
            oeuvreChoisi = this.renderer.choisirUneCarteOeuvreExposee(carteOeuvreExposee);
        }
        joueurAppelant.getOeuvreExposee().getCartes().remove(oeuvreChoisi);
        joueurAppelant.getMain().add(oeuvreChoisi);
    }
}