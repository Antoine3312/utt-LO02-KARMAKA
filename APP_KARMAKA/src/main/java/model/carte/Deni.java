package model.carte;

import java.util.List;

public class Deni extends Carte{

    public Deni(){
        this.point = 2;
        this.couleur = NomCouleur.BLEU;
    }
    public void pouvoir(List<Carte> mainJoueur){
        //verifie si la liste main Joueur n'est pas vide.
        if (!mainJoueur.isEmpty()) {
            Carte carteDefaussee = mainJoueur.remove(0);
            System.out.println("Carte defaussee : " + carteDefaussee);
        } else {
            System.out.println("La main du joueur est vide. Aucune carte n'a été défaussée.");
        }
    }

    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
