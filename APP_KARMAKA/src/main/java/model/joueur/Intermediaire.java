package model.joueur;

public class Intermediaire implements StyleJeuStrategy{

    @Override
    public void jouerTour() {
        System.out.println("Jouer un tour en intermédaire");
    }
}
