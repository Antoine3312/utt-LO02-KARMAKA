package model.carte;

import java.util.List;

public class Vol extends Carte{

    public Vol(){
        this.point = 3;
        this.couleur = NomCouleur.BLEU;
    }
    public void pouvoir(List<Carte> mainJoueur, Carte oeuvreExposeeRival){
        // Placez dans votre Main l'Oeuvre Exposée d'un rival.
        mainJoueur.add(oeuvreExposeeRival);
    }

    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
