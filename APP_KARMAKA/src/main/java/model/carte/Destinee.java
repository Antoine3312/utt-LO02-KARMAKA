/**
 * La classe {@code Destinee} représente une carte du jeu avec le pouvoir spécifique de regarder
 * les trois premières cartes de la source, puis d'ajouter deux d'entre elles dans la Vie Future du joueur.
 * Elle hérite de la classe abstraite {@link Carte}.
 */
package model.carte;

import application.control.Renderable;
import model.EtatPartie;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Destinee extends Carte {

    /**
     * Constructeur de la classe Destinee.
     *
     * @param renderable L'objet permettant d'afficher des messages dans l'interface utilisateur.
     */
    public Destinee(Renderable renderable) {
        super(renderable);
        this.nom = "Destinee";
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
        this.intitulePouvoir = "Regardez les 3 premières cartes de la Source, ajoutez-en 2 dans votre Vie Future";
    }

    /**
     * Méthode pour jouer le pouvoir de la carte Destinee.
     *
     * @param joueurAppelant Le joueur qui utilise la carte.
     * @param joueurReceveur Le joueur sur lequel la carte est jouée.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        this.renderer.displayMessage(String.format("%s utilise la carte %s", joueurAppelant.getNom(), this.getNom()));
        EtatPartie etatPartie = EtatPartie.getInstance();
        // Vérifie si la source a au moins trois cartes
        if (!etatPartie.getSource().getCartes().isEmpty()) {
            // Récupère les trois premières cartes de la source
            List<Carte> troisPremièresCarteSource = Arrays.asList(
                    etatPartie.getSource().getCartes().pop(),
                    etatPartie.getSource().getCartes().pop(),
                    etatPartie.getSource().getCartes().pop()
            );

            // Si le joueur appelant n'est pas un ordinateur, permet au joueur de choisir deux cartes à ajouter dans sa Vie Future
            if (!(joueurAppelant instanceof Ordinateur)) {
                List<Carte> carteAAjouter = this.renderer.choisirDeuxCarte(troisPremièresCarteSource);
                joueurAppelant.getVieFutur().getCartes().addAll(carteAAjouter);
                troisPremièresCarteSource.removeAll(carteAAjouter);
            } else {
                // Si le joueur appelant est un ordinateur, choisit aléatoirement deux cartes à ajouter dans sa Vie Future
                List<Carte> carteAAjouter = null;
                Random r = new Random();
                for (int i = 0; i < 2; i++) {
                    carteAAjouter.add(troisPremièresCarteSource.get(r.nextInt(troisPremièresCarteSource.size())));
                    troisPremièresCarteSource.removeAll(carteAAjouter);
                }
            }

            // Ajoute les cartes restantes dans la source
            etatPartie.getSource().getCartes().addAll(troisPremièresCarteSource);
        } else {
            this.renderer.displayErrorMessage("Impossible de jouer la carte car la source est vide.");
        }
    }
}
