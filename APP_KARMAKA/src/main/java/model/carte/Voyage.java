package model.carte;

public class Voyage extends Carte{

    public Voyage(){
        this.point = 3;
        this.couleur = NomCouleur.VERTE;
    }
    // Méthode pour puiser 3 cartes à la source
    public void puiserTroisCartesALaSource() {
        // Implémentez ici la logique pour puiser 3 cartes à la source
        System.out.println("Vous puisez 3 cartes à la source.");
    }

    // Méthode pour jouer une autre carte après avoir puisé à la source
    public void jouerAutreCarte() {
        // Implémentez ici la logique pour jouer une autre carte
        System.out.println("Vous pouvez maintenant jouer une autre carte.");
    }

    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }

}
