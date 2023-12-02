package model.carte;

public class Carte1 extends Carte{

    public Carte1(String nom, int point, NomCouleur couleur) {
        super(nom, point, couleur);
    }

    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
