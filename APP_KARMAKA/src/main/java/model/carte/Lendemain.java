package model.carte;
import java.util.List;
import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

import java.util.Random;

public abstract class Lendemain extends Carte{

    public Lendemain(Renderable renderable){
        super(renderable);
        this.point = 1;
        this.couleur = NomCouleur.VERTE;
    }


    @Override

        public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {

                Carte cartePiochee = null;
                List<Carte> carteSourceJoueurAppelant = joueurAppelant.getSource().getCartes();

                if (!carteSourceJoueurAppelant.isEmpty()) {
                    // Piocher la première carte de la source du joueur appelant
                    cartePiochee = carteSourceJoueurAppelant.get(0);

                    // Retirer la carte de la Source du joueur appelant
                    joueurAppelant.getSource().getCartes().remove(cartePiochee);

                    // Ajouter la carte à la main du joueur appelant
                    joueurAppelant.getMain().add(cartePiochee);

                    System.out.println("Vous avez puisé la carte Lendemain de la Source.");

                    // Permettre au joueur d'effectuer une autre action si nécessaire
                    // (vous devrez implémenter la logique associée à cette action supplémentaire)
                } else {
                    System.out.println("La Source est vide, vous ne pouvez pas piocher la carte Lendemain.");
                }
            }
        }
