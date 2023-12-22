package model.joueur;

import application.control.ActionJouer;
import application.control.Renderable;
import model.carte.Carte;
import model.carte.NomCouleur;

import java.io.Serializable;
import java.util.*;

public class Expert implements StyleJeuStrategy, Serializable {
    private static final long serialVersionUID = 2711998155099132322L;
    private NomCouleur couleurPoint = null;
    private NomCouleur couleurFutur = null;
    private NomCouleur couleurPouvoir = null;

    public ActionJouer actionJouer;

    public Random r = new Random();
    private Renderable renderer;

    private boolean hasInit = false;

    @Override
    public void jouerTour(Joueur joueur, Renderable renderer) {
        this.renderer = renderer;
        this.actionJouer = new ActionJouer(this.renderer);
        if (!this.hasInit){
            this.choisirCouleurCartes(joueur.getMain());
            this.hasInit = false;
        }

        if (joueur.getMain().isEmpty() && joueur.getPile().getCartes().isEmpty()){
            this.reincarner(joueur);
            this.choisirCouleurCartes(joueur.getMain());
        } else {
            this.jouer(joueur);
        }
    }

    private void jouer(Joueur joueur) {
        if(!joueur.getPile().getCartes().isEmpty()){
            joueur.getMain().add(joueur.getPile().getCartes().pop());
        }
        boolean jouerCarte = !(this.r.nextInt(4) == 1);
        if(jouerCarte) {
            Carte carteAJouer  = joueur.getMain().get(this.r.nextInt(joueur.getMain().size()));
            if(carteAJouer.getCouleur().equals(this.couleurPouvoir)){
                this.actionJouer.jouer(joueur, carteAJouer, ActionJouer.UTILISATIONPOUVOIR);
            } else if(carteAJouer.getCouleur().equals(this.couleurPoint)) {
                this.actionJouer.jouer(joueur, carteAJouer, ActionJouer.UTILISATIONPOINT);
            } else if(carteAJouer.getCouleur().equals(this.couleurFutur)) {
                this.actionJouer.jouer(joueur, carteAJouer, ActionJouer.UTILISATIONFUTUR);
            } else if (carteAJouer.getCouleur().equals(NomCouleur.MOSAIQUE)){
                this.actionJouer.jouer(joueur, carteAJouer, (r.nextInt(3))+1);
            } else {
                System.out.println("null");
            }
        } else {
            this.renderer.displayErrorMessage(String.format("%s décide de passer son tour. Attention, il prépare sûrement un plan diabolique ...", joueur.getNom()));
        }
    }

    private void reincarner(Joueur joueur) {
        this.renderer.displayMessage(String.format("%s n'a plus aucune carte dans sa main et dans sa pile, il va se réincarner ...", joueur.getNom()));
        NomCouleur couleurLaPlusRentable = null;
        if(!joueur.getOeuvre().getCartes().isEmpty()) {
            List<String> couleurs = new ArrayList<>(joueur.getOeuvre().getCouleursInStack());
            couleurLaPlusRentable = NomCouleur.valueOf(couleurs.get(r.nextInt(couleurs.size())));
        }
        boolean utiliserAnneaux = this.r.nextInt(2) == 1;
        if(utiliserAnneaux){
            this.actionJouer.reincarner(joueur, couleurLaPlusRentable, true, this.r.nextInt(joueur.getNbAnneauxKarmique()+ 1) );
        } else {
            this.actionJouer.reincarner(joueur, couleurLaPlusRentable, false, 0);
        }

    }

    private void choisirCouleurCartes(List<Carte> main){
        List<NomCouleur> couleurs = Arrays.asList(NomCouleur.BLEU, NomCouleur.ROUGE, NomCouleur.VERTE);
        Map<NomCouleur, Integer> couleurCarteMain = new HashMap<>();
        couleurs.forEach(c -> couleurCarteMain.put(c, 0));
        for(Carte c : main){
            if(c.getCouleur() != NomCouleur.MOSAIQUE){
                couleurCarteMain.put(c.getCouleur(), couleurCarteMain.get(c.getCouleur())+1);
            }
        }
        List<Map.Entry<NomCouleur, Integer>> entryList = new ArrayList<>(couleurCarteMain.entrySet());
        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        this.couleurPoint = entryList.get(0).getKey();
        this.couleurFutur = entryList.get(1).getKey();
        this.couleurPouvoir = entryList.get(2).getKey();
    }
}
