package model.joueur;

public class Ordinateur implements Playable{
    private StyleJeuStrategy strategy;

    public Ordinateur(StyleJeuStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeTour(){
        this.strategy.jouerTour();
    }
}
