package model.joueur;

public class Ordinateur extends Joueur{
    private StyleJeuStrategy strategy;

    public Ordinateur(String nom, StyleJeuStrategy strategy) {
        super(nom);
        this.strategy = strategy;
    }

    public void executeTour(){
        this.strategy.jouerTour();
    }

    public boolean isBot(){
        return true;
    }
}
