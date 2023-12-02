package model.carte;

public class DernierSouffle extends Carte{

    public DernierSouffle(){
        this.point = 1;
        this.couleur = NomCouleur.ROUGE;
    }


    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
