package model.carte;

public class Carte1 extends Carte{
    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
