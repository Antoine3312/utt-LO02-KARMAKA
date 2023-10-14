package model.joueur;

public class Expert implements StyleJeuStrategy{
    @Override
    public void jouerTour() {
        System.out.println("Jouer un tour en expert !");
    }
}
