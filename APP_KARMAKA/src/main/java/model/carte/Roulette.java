package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.List;
import java.util.Random;

/**
 * Classe abstraite représentant la carte "Roulette" dans le jeu.
 * Hérite de la classe abstraite "Carte".
 */
public class Roulette extends Carte {

    /**
     * Constructeur de la carte "Roulette".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Roulette(Renderable renderable) {
        super(renderable);
        this.nom = "Roulette";
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.ROUGE; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Roulette".
     * Défausse jusqu'à 2 cartes de votre Main, puis pioche à la Source autant de cartes que défaussées + 1.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Le joueur adverse (non utilisé dans ce cas).
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        if(EtatPartie.getInstance().getSource().getCartes().size()>=3 && joueurAppelant.getMain().size()>=2){
            if(!(joueurAppelant instanceof Ordinateur)){
                List<Carte> cartesChoisies = this.renderer.choisirDeuxCarte(joueurAppelant.getMain());
                EtatPartie.getInstance().getFosse().getCartes().addAll(cartesChoisies);
                joueurAppelant.getMain().removeAll(cartesChoisies);
            } else {
                Random r = new Random();
                for (int i=0; i<2; i++){
                    Carte carteADefausser = joueurAppelant.getMain().get(r.nextInt(joueurAppelant.getMain().size()));
                    EtatPartie.getInstance().getFosse().getCartes().add(carteADefausser);
                    joueurAppelant.getMain().remove(carteADefausser);
                }
            }
            for (int i=0; i<3; i++){
                joueurAppelant.getMain().add(EtatPartie.getInstance().getSource().getCartes().pop());
            }
        } else {
            this.renderer.displayErrorMessage("Impossible : Le joueur appellant a moins de 2 cartes en main, et il y a moins de 3 cartes dans la pioche.");
        }
    }
}
