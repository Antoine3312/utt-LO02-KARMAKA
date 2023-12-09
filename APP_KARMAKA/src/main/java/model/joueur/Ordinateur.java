package model.joueur;

public class Ordinateur extends Joueur{
    private StyleJeuStrategy strategy;

    public Ordinateur(String nom, StyleJeuStrategy strategy) {
        super(nom);
        this.strategy = strategy;
    }

    public void executeTour(){
        this.strategy.jouerTour(this);
    }

    public boolean isBot(){
        return true;
    }

    @Override
    public String toString() {
        return super.nom+" "+this.strategy.getClass().getName().split("\\.")[2];
    }
}
