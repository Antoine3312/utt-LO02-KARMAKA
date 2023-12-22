package model.joueur;

import application.control.Renderable;

import java.io.Serializable;

public class Ordinateur extends Joueur implements Serializable {
    private final Renderable renderer;
    private StyleJeuStrategy strategy;

    public Ordinateur(String nom, StyleJeuStrategy strategy, Renderable renderer) {
        super(nom);
        this.strategy = strategy;
        this.renderer = renderer;
    }

    public void executeTour(){
        this.strategy.jouerTour(this, this.renderer);
    }

    public boolean isBot(){
        return true;
    }

    @Override
    public String toString() {
        return super.nom+" "+this.strategy.getClass().getName().split("\\.")[2];
    }
}
