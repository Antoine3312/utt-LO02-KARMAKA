package model.carte;

public class Roulette extends Carte{

    public Roulette(){
        this.point = 2;
        this.couleur = NomCouleur.ROUGE;
    }


    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
