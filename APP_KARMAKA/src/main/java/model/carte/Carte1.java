package model.carte;

public class Carte1 extends Carte{
    public int test;
    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
