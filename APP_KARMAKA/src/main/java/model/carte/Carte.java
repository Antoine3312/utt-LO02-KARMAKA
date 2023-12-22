package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;

public abstract class Carte {
    protected String nom;
    protected int point;
    protected NomCouleur couleur;
    protected Renderable renderer;

    public Carte(Renderable renderer) {
        this.renderer = renderer;
    }

    public Carte(String nom, int point, NomCouleur couleur) {
        this.nom = nom;
        this.point = point;
        this.couleur = couleur;
    }

    public void jouerPoint(Joueur joueurAppelant){
        this.renderer.displayMessage(String.format("%s utilise la carte %s pour ses points.", joueurAppelant, this.nom));
        joueurAppelant.getOeuvre().getCartes().push(this);
    }
    public void jouerFutur(Joueur joueurAppelant){
        this.renderer.displayMessage(String.format("%s utilise la carte %s pour sa vie futur.", joueurAppelant, this.nom));
        joueurAppelant.getVieFutur().getCartes().push(this);
    }

    public abstract void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur);

    public String getNom() {
        return nom;
    }

    public int getPoint() {
        return point;
    }

    public NomCouleur getCouleur() {
        return couleur;
    }

    @Override
    public String toString() {
        return "("+point+")"+"  "+nom+"  ["+couleur+"]";
    }

}
