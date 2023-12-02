package model.carte;

public class Incarnation extends Carte{

    public Incarnation(){
        this.point = 1;
        this.couleur = NomCouleur.MOSAIQUE;
    }


    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
