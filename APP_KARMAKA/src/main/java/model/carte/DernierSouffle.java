package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

// Carte "Dernier Souffle" qui hérite de la classe abstraite "Carte"
public abstract class DernierSouffle extends Carte {

    // Constructeur de la carte "Dernier Souffle"
    public DernierSouffle(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
    }

    // Implémentation de la méthode jouerPouvoir() définie dans l'interface PouvoirCarte
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Si le joueur appelant est un ordinateur
        if (joueurAppelant instanceof Ordinateur) {
            // Appel de la méthode pour défausser automatiquement une carte
            defausserAutomatiquement(joueurReceveur);
        } else {
            // Si le joueur est humain
            List<Carte> mainJoueurReceveur = joueurReceveur.getMain().getCartes();
            this.renderer.afficherCartes(mainJoueurReceveur);

            // Le joueur humain choisit une carte à défausser
            Carte carteADefausser = this.renderer.choisirUneCarte(mainJoueurReceveur);

            // Si le joueur a effectivement choisi une carte
            if (carteADefausser != null) {
                // Retirer la carte de la main du joueur receveur
                joueurReceveur.getMain().getCartes().remove(carteADefausser);
                // Ajouter la carte à la défausse du joueur receveur
                joueurReceveur.getDefausse().getCartes().add(carteADefausser);
            }
        }
    }

    // Méthode pour défausser automatiquement une carte (utilisée par l'ordinateur)
    private void defausserAutomatiquement(Joueur joueur) {
        // Logique pour que l'ordinateur choisisse et défausse automatiquement une carte de sa main
        // Vous pouvez adapter cette logique en fonction des règles spécifiques du jeu
        List<Carte> cartesDansLaMain = joueur.getMain().getCartes();

        // S'il y a des cartes dans la main du joueur
        if (!cartesDansLaMain.isEmpty()) {
            // Choix aléatoire d'une carte à défausser
            Carte carteADefausser = cartesDansLaMain.get(new Random().nextInt(cartesDansLaMain.size()));

            // Retirer la carte de la main du joueur
            joueur.getMain().getCartes().remove(carteADefausser);
            // Ajouter la carte à la défausse du joueur
            joueur.getDefausse().getCartes().add(carteADefausser);
        }
    }
}
