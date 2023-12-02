package model.carte;

public class Duperie extends Carte{

    public Duperie(){
        this.point = 3;
        this.couleur = NomCouleur.BLEU;
    }


    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
