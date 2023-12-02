package model.carte;

public class Panique extends Carte{

    public Panique(){
        this.point = 1;
        this.couleur = NomCouleur.ROUGE;
    }


    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
