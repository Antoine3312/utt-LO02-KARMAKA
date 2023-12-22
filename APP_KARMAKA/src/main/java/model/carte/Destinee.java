package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Carte "Destinee" qui hérite de la classe abstraite "Carte".
 * Représente une carte permettant au joueur appelant de regarder les 3 premières cartes de la Source,
 * de choisir jusqu'à 2 cartes à ajouter à sa Vie Future, et de replacer le reste dans l'ordre souhaité.
 */
public class Destinee extends Carte {

    /**
     * Constructeur de la carte "Destinee".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Destinee(Renderable renderable) {
        super(renderable);
        this.nom = "Destinee";
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
        this.intitulePouvoir = "Regardez les 3 premières cartes de la Source, ajoutez-en 2 dans votre Vie Future";
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Destinee".
     * Le joueur appelant regarde les 3 premières cartes de la Source,
     * choisit jusqu'à 2 cartes à ajouter à sa Vie Future,
     * et replace le reste dans l'ordre souhaité.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Non utilisé dans ce contexte.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s", joueurAppelant.getNom(), this.getNom()));
        EtatPartie etatPartie = EtatPartie.getInstance();
        if(!etatPartie.getSource().getCartes().isEmpty()){
            List<Carte> troisPremièresCarteSource = Arrays.asList(etatPartie.getSource().getCartes().pop(), etatPartie.getSource().getCartes().pop(), etatPartie.getSource().getCartes().pop());
            if(!(joueurAppelant instanceof Ordinateur)) {
                List<Carte> carteAAjouter = this.renderer.choisirDeuxCarte(troisPremièresCarteSource);
                joueurAppelant.getVieFutur().getCartes().addAll(carteAAjouter);
                troisPremièresCarteSource.removeAll(carteAAjouter);
            } else {
                List<Carte> carteAAjouter = null;
                Random r = new Random();
                for(int i=0; i>2 ;i++){
                    carteAAjouter.add(troisPremièresCarteSource.get(r.nextInt(troisPremièresCarteSource.size())));
                    troisPremièresCarteSource.removeAll(carteAAjouter);
                }
            }
            etatPartie.getSource().getCartes().addAll(troisPremièresCarteSource);
        } else {
            this.renderer.displayErrorMessage("Impossible de jouer la carte car la source est vide.");
        }
    }
}
