package model.carte;

public class Fournaise extends Carte{

    public Fournaise(){
        this.point = 2;
        this.couleur = NomCouleur.ROUGE;
    }


    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
