package model.carte;



public class Semis extends Carte{
    private void placerDeuxCartesSurVieFuture() {
    }
    private void puiserDeuxCartesALaSource() {
    }
    public Semis(){
        this.point = 2;
        this.couleur = NomCouleur.VERTE;
    }
    public void jouer() {
        puiserDeuxCartesALaSource();
        placerDeuxCartesSurVieFuture();

        // Méthode pour puiser 2 cartes à la Source
        private void puiserDeuxCartesALaSource() {
            System.out.println("Puisez 2 cartes à la Source.");

            // Méthode pour placer 2 cartes sur la Vie Future depuis la Main
            private void placerDeuxCartesSurVieFuture() {
                System.out.println("Placez sur votre Vie Future 2 cartes de votre Main.");
    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}

