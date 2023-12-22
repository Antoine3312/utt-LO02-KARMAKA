package model.joueur;

import application.control.Renderable;

import java.io.Serializable;

/**
 * La classe Ordinateur représente un joueur contrôlé par l'ordinateur.
 * Elle étend la classe Joueur et implémente Serializable.
 */
public class Ordinateur extends Joueur implements Serializable {
    private final Renderable renderer;
    private StyleJeuStrategy strategy;

    /**
     * Constructeur de la classe Ordinateur.
     *
     * @param nom      Le nom de l'ordinateur.
     * @param strategy La stratégie de jeu de l'ordinateur.
     * @param renderer L'objet Renderable pour afficher des messages.
     */
    public Ordinateur(String nom, StyleJeuStrategy strategy, Renderable renderer) {
        super(nom);
        this.strategy = strategy;
        this.renderer = renderer;
    }

    /**
     * Méthode pour exécuter le tour de l'ordinateur.
     */
    public void executeTour() {
        this.strategy.jouerTour(this, this.renderer);
    }

    /**
     * Méthode pour vérifier si l'instance est un bot.
     *
     * @return true si l'instance est un bot, sinon false.
     */
    public boolean isBot() {
        return true;
    }

    /**
     * Méthode pour obtenir la représentation textuelle de l'ordinateur.
     *
     * @return La chaîne représentant l'ordinateur avec son nom et sa stratégie.
     */
    @Override
    public String toString() {
        return super.nom + " " + this.strategy.getClass().getName().split("\\.")[2];
    }
}
