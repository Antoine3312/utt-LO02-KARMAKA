package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe abstraite représentant la carte "Semis" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public class Semis extends Carte {

    /**
     * Constructeur de la carte "Semis".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Semis(Renderable renderable) {
        super(renderable);
        this.nom = "Semis";
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.VERTE; // Définition de la couleur de la carte
        this.intitulePouvoir = "Placez 2 cartes à la Source, puis placez sur votre Vie Future 2 cartes de votre Main";
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Semis".
     * Puisez 2 cartes à la Source et placez sur votre Vie Future 2 cartes de votre Main.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur adverse (non utilisé dans ce contexte).
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s", joueurAppelant.getNom(), this.getNom()));
        if(EtatPartie.getInstance().getSource().getCartes().size()>=2) {
            for (int i =0; i<2; i++){
                joueurAppelant.getMain().add(EtatPartie.getInstance().getSource().getCartes().pop());
            }
            List<Carte> cartesChoisies = new ArrayList<>();
            if(!(joueurAppelant instanceof Ordinateur)){
                cartesChoisies = this.renderer.choisirDeuxCarte(joueurAppelant.getMain());
                joueurAppelant.getMain().removeAll(cartesChoisies);
            } else {
                Random r = new Random();
                for(int i =0 ;i<2; i++){
                    cartesChoisies.add(joueurAppelant.getMain().get(r.nextInt(joueurAppelant.getMain().size())));
                    joueurAppelant.getMain().removeAll(cartesChoisies);
                }
            }
            joueurAppelant.getVieFutur().getCartes().addAll(cartesChoisies);
        } else {
            this.renderer.displayErrorMessage("Impossible : Il y a moins de 2 cartes dans la source.");
        }
    }

}

