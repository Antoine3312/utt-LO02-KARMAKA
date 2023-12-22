package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;

/**
 * Classe abstraite représentant la carte "Jubilé" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public class Jubile extends Carte {

    /**
     * Constructeur de la carte "Jubilé".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Jubile(Renderable renderable) {
        super(renderable);
        this.nom = "Jubile";
        this.point = 3; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Placez 2 cartes de votre Main sur vos Oeuvres";
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Jubilé".
     * Le joueur humain choisit jusqu'à 2 cartes de sa main à placer sur ses œuvres.
     * L'ordinateur choisit automatiquement jusqu'à 2 cartes à placer.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur cible du pouvoir.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s sur %s", joueurAppelant.getNom(), this.getNom(), joueurReceveur.getNom()));
        if(joueurAppelant.getMain().size()>= 2){
            if (!(joueurAppelant instanceof Ordinateur)){
                List<Carte> carteChoisies = this.renderer.choisirDeuxCarte(joueurAppelant.getMain());
                joueurAppelant.getMain().removeAll(carteChoisies);
                joueurAppelant.getOeuvre().getCartes().addAll(carteChoisies);
            } else {
                Random r = new Random();
                for(int i = 0; i<2; i++){
                    Carte carteChoisi = joueurAppelant.getMain().get(r.nextInt(joueurAppelant.getMain().size()));
                    joueurAppelant.getOeuvre().getCartes().push(carteChoisi);
                    joueurAppelant.getMain().remove(carteChoisi);
                }
            }
        } else {
            this.renderer.displayErrorMessage("Impossible : Vous n'avez pas assez de carte dans votre main.");
        }

    }
}
