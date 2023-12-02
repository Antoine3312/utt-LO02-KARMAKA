package model.carte;

public class Vengeance extends Carte{

    public Vengeance(){
        this.point = 3;
        this.couleur = NomCouleur.ROUGE;
    }


    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
