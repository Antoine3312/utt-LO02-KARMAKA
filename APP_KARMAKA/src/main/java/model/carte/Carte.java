package model.carte;

public abstract class Carte {
    protected String nom;
    protected int point;
    protected NomCouleur couleur;

    public Carte(String nom, int point, NomCouleur couleur) {
        this.nom = nom;
        this.point = point;
        this.couleur = couleur;
    }

    public void jouerPoint(){
        System.out.println("Joueur point");
    }
    public void jouerFutur(){
        System.out.println("Joueur futur");
    }

    public abstract void jouerPouvoir();

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
