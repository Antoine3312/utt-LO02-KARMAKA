package model.carte;

public class Recyclage extends Carte{

    public Recyclage(){
        this.point = 1;
        this.couleur = NomCouleur.VERTE;
    }


    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
