package model.carte;

public class Destinee extends Carte{

    public Destinee(){
        this.point = 2;
        this.couleur = NomCouleur.BLEU;
    }


    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
