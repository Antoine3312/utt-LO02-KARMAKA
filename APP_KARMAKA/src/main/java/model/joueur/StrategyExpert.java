package model.joueur;

public class StrategyExpert implements StyleJeuStrategy{
    @Override
    public void jouerTour() {
        System.out.println("Jouer un tour en expert !");
    }
}
