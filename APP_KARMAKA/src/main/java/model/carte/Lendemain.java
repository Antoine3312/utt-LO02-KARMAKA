package model.carte;

public class Lendemain extends Carte{

    public Lendemain(){
        this.point = 1;
        this.couleur = NomCouleur.VERTE;
    }


    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
