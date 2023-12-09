package model.carte;

import model.joueur.Joueur;

import java.util.List;

public class RevesBrises extends Carte{

    private Joueur joueurAppelant;
    private Joueur joueurRival;

    public RevesBrises(){
        this.point = 2;
        this.couleur = NomCouleur.BLEU;
    }
    @Override

    public void placerPremiereCarteVieFuturRivalSurLaVotre(Joueur joueurAppelant, Joueur joueurRival) {

        // Récupérer la première carte de la Vie Future du joueur rival
        List<Carte> carteVieFuturRival = joueurRival.getVieFutur().getCartes();

        if (!carteVieFuturRival.isEmpty()) {
            // Récupérer la première carte de la Vie Future du joueur rival
            Carte carteRival = carteVieFuturRival.get(0);

            // Retirer la carte de la Vie Future du joueur rival
            joueurRival.getVieFutur().getCartes().remove(carteRival);

            // Ajouter la carte à la Vie Future du joueur appelant
            joueurAppelant.getVieFutur().getCartes().add(carteRival);

            System.out.println("Vous avez placé la première carte de la Vie Future de votre rival sur la vôtre.");
        } else {
            System.out.println("Le joueur rival n'a pas de carte dans sa Vie Future à placer sur la vôtre.");
        }
    }




