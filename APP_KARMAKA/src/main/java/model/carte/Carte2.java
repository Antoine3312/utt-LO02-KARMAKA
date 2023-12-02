package model.carte;

public class Carte2 extends Carte{

    public Carte2(String nom, int point, NomCouleur couleur) {
        super(nom, point, couleur);
    }

    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
