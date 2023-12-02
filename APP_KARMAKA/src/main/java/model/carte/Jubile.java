package model.carte;

public class Jubile extends Carte{

    public Jubile(){
        this.point = 3;
        this.couleur = NomCouleur.VERTE;
    }


    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
