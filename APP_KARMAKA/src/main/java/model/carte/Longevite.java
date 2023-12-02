package model.carte;

public class Longevite extends Carte{

    public Longevite(){
        this.point = 2;
        this.couleur = NomCouleur.VERTE;
    }


    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
