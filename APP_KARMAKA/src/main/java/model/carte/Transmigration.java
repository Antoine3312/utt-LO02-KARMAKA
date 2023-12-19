package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;

// Carte "Transmigration" qui hérite de la classe abstraite "Carte"
public abstract class Transmigration extends Carte {

    // Constructeur de la carte "Transmigration"
    public Transmigration(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
    }

    // Implémentation de la méthode jouerPouvoir() définie dans l'interface PouvoirCarte
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        Carte carteChoisi = null;
        List<Carte> carteVieFutur = joueurAppelant.getVieFutur().getCartes();

        // Si le joueur appelant est un ordinateur, il choisit aléatoirement une carte Vie Futur à transférer
        if (joueurAppelant instanceof Ordinateur) {
            carteChoisi = carteVieFutur.get(new Random().nextInt(carteVieFutur.size()));
        } else {
            // Si le joueur est humain, il choisit une carte Vie Futur à transférer
            this.renderer.afficherCartes(carteVieFutur);
            carteChoisi = this.renderer.choisirUneCarteVieFutur(carteVieFutur);
        }

        // Retirer la carte Vie Futur choisie du joueur appelant et la placer dans sa main
        joueurAppelant.getVieFutur().getCartes().remove(carteChoisi);
        joueurAppelant.getMain().add(carteChoisi);
    }
}
