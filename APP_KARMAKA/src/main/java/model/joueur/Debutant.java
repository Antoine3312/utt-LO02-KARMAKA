package model.joueur;

public class Debutant implements StyleJeuStrategy {

    @Override
    public void jouerTour() {
        System.out.println("Jouer un tour en débutant");
    }
}
