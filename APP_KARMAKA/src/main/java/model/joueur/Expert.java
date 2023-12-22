package model.joueur;

import application.control.ActionJouer;
import application.control.Renderable;
import model.carte.Carte;
import model.carte.NomCouleur;

import java.util.*;

public class Expert implements StyleJeuStrategy{

    private NomCouleur couleurPoint = null;
    private NomCouleur couleurFutur = null;
    private NomCouleur couleurPouvoir = null;

    public ActionJouer actionJouer;

    public Random r = new Random();
    private Renderable renderer;

    @Override
    public void jouerTour(Joueur joueur, Renderable renderer) {
        this.renderer = renderer;
        this.actionJouer = new ActionJouer(this.renderer);
        if (joueur.getMain().isEmpty() && joueur.getPile().getCartes().isEmpty()){
            this.choisirCouleurCartes(joueur.getMain());
            this.reincarner(joueur);
        } else {
            this.jouer(joueur);
        }
    }

    private void jouer(Joueur joueur) {
        boolean jouerCarte = !(this.r.nextInt(4) == 1);
        if(jouerCarte) {
            Carte carteAJouer  = joueur.getMain().get(this.r.nextInt(joueur.getMain().size()));
            if(carteAJouer.getCouleur().equals(this.couleurPouvoir)){
                this.actionJouer.jouer(joueur, true, carteAJouer, ActionJouer.UTILISATIONPOUVOIR);
            } else if(carteAJouer.getCouleur().equals(this.couleurPoint)) {
                this.actionJouer.jouer(joueur, true, carteAJouer, ActionJouer.UTILISATIONPOINT);
            } else if(carteAJouer.getCouleur().equals(this.couleurFutur)) {
                this.actionJouer.jouer(joueur, true, carteAJouer, ActionJouer.UTILISATIONFUTUR);
            } else if (carteAJouer.getCouleur().equals(NomCouleur.MOSAIQUE)){
                this.actionJouer.jouer(joueur, true, carteAJouer, (r.nextInt(3))+1);
            }
        } else {
            this.actionJouer.jouer(joueur, false, null, 0);
            this.renderer.displayErrorMessage(String.format("%s décide de passer son tour. Attention, il prépare sûrement un plan diabolique ...", joueur.getNom()));
        }
    }

    private void reincarner(Joueur joueur) {
        List<String> couleurs = new ArrayList<>(joueur.getOeuvre().getCouleursInStack());
        NomCouleur couleurLaPlusRentable = NomCouleur.valueOf(couleurs.get(r.nextInt(couleurs.size())));
        boolean utiliserAnneaux = this.r.nextInt(2) == 1;
        if(utiliserAnneaux){
            this.actionJouer.reincarner(joueur, couleurLaPlusRentable, true, (this.r.nextInt(joueur.getNbAnneauxKarmique())) + 1);
        } else {
            this.actionJouer.reincarner(joueur, couleurLaPlusRentable, false, 0);
        }
        this.choisirCouleurCartes(joueur.getMain());
    }

    private void choisirCouleurCartes(List<Carte> main){
        List<NomCouleur> couleurs = Arrays.asList(NomCouleur.BLEU, NomCouleur.ROUGE, NomCouleur.VERTE);
        Map<NomCouleur, Integer> couleurCarteMain = new HashMap<>();
        couleurs.forEach(c -> couleurCarteMain.put(c, 0));

        for(Carte c : main){
            couleurCarteMain.put(c.getCouleur(), couleurCarteMain.get(c.getCouleur())+1);
        }
        List<Map.Entry<NomCouleur, Integer>> entryList = new ArrayList<>(couleurCarteMain.entrySet());
        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        this.couleurPoint = entryList.get(0).getKey();
        this.couleurFutur = entryList.get(1).getKey();
        this.couleurPouvoir = entryList.get(2).getKey();
    }
}
