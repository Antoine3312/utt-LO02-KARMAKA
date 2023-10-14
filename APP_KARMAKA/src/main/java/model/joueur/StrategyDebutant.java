package model.joueur;

public class StrategyDebutant implements StyleJeuStrategy {

    @Override
    public void jouerTour() {
        System.out.println("Jouer un tour en débutant");
    }
}
