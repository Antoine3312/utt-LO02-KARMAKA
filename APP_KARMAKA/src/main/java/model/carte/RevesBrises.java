package model.carte;

import java.util.List;

public class RevesBrises extends Carte{

    public RevesBrises(){
        this.point = 2;
        this.couleur = NomCouleur.BLEU;
    }
    public void pouvoir(List<Carte> vieFutureRival, List<Carte> vieFutureJoueur){
        // Placez la première carte de la Vie Future d'un rival sur la vôtre.
        if (!vieFutureRival.isEmpty()) {
            Carte carteRival = vieFutureRival.remove(0);
            vieFutureJoueur.add(carteRival);
        }
    }

    @Override
    public void jouerPouvoir(){
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
