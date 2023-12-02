package model.carte;

import java.util.List;

public class CoupDOeil extends Carte{

    public CoupDOeil(){
        this.point = 1;
        this.couleur = NomCouleur.BLEU;
    }
    //Affiche la main du rival
    public void pouvoir(List<Carte> mainRival){
        System.out.println("Main du rival avant CoupDOeil : " + mainRival);
    }

    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
