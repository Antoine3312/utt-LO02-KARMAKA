package model.carte;

public class Mimetisme extends Carte{

    public Mimetisme(){
        this.point = 1;
        this.couleur = NomCouleur.MOSAIQUE;
    }


    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
