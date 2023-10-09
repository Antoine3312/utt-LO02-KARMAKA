package model.joueur;

public class StrategyDebutant extends ActionJouer implements StyleJeuStrategy {

    @Override
    public void jouerTour() {
        System.out.println("Jouer un tour en débutant");
    }
}
