package model.carte;

import java.util.List;

public class Transmigration extends Carte{

    private List<Carte> vieFuture;
    private Carte carte;

    public Transmigration(){
    this.point = 1;
    this.couleur = NomCouleur.BLEU;
}
}
    public void pouvoir(List<Carte> vieFuture, Carte carte) {
        this.vieFuture = vieFuture;
        this.carte = carte;
        /* Placez dans votre Main n'importe quelle carte de votre Vie Future. */
        vieFuture.remove(carte);
}
    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
