package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Carte "Deni" qui hérite de la classe abstraite "Carte".
 * Représente une carte permettant au joueur de défausser une carte de sa main et de copier le pouvoir de la carte défaussée.
 */
public abstract class Deni extends Carte {

    /**
     * Constructeur de la carte "Deni".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Deni(Renderable renderable) {
        super(renderable);
        this.nom = "Deni";
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Deni".
     * Le joueur appelant défausse une carte de sa main et copie le pouvoir de la carte défaussée.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur cible du pouvoir (non utilisé dans ce contexte).
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s", joueurAppelant.getNom(), this.getNom()));
        // Le joueur appelant défausse une carte de sa main
        Carte carteDefausser = defausserCarte(joueurAppelant);

        // Copiez le pouvoir de la carte défaussée
        copierPouvoirCarte(carteDefausser, joueurAppelant, joueurReceveur);
    }

    /**
     * Méthode privée pour défausser une carte de la main du joueur.
     *
     * @param joueur Le joueur qui défausse la carte.
     */
    private Carte defausserCarte(Joueur joueur) {
        Carte carteChoisi = null;
        if(!joueur.getMain().isEmpty()){
            if(!(joueur instanceof Ordinateur)){
                carteChoisi = this.renderer.choisirUneCarte(joueur.getMain());
            } else {
                Random r = new Random();
                carteChoisi = joueur.getMain().get(r.nextInt(joueur.getMain().size()));
            }
            // Défausser la carte choisie par le joueur
            EtatPartie.getInstance().getFosse().getCartes().add(carteChoisi);
            joueur.getMain().remove(carteChoisi);
        }
        return carteChoisi;
    }

    /**
     * Méthode privée pour copier le pouvoir de la carte défaussée.
     *
     * @param carteDefausser La carte à utiliser le pouvoir
     */
    private void copierPouvoirCarte(Carte carteDefausser, Joueur joueurAppelant, Joueur joueurReceveur) {
        // Logique pour copier le pouvoir de la carte défaussée (appliquer son pouvoir)
        if(carteDefausser != null){
            carteDefausser.jouerPouvoir(joueurAppelant, joueurReceveur);
        }
    }
}


