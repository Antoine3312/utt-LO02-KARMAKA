package model.carte;

public class Crise extends Carte{

    public Crise(){
        this.point = 2;
        this.couleur = NomCouleur.ROUGE;
    }


    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
